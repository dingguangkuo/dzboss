package com.smartdz.dzboss.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Objects;
import java.util.UUID;

/**
 * 监控url请求
 */
@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger Log = LoggerFactory.getLogger(ControllerInterceptor.class);
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static ThreadLocal<String> key = new ThreadLocal<>();
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 拦截规则：
     * 拦截 RequestMapping 注释的方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    /**
     * 请求方法前打印内容
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        // 请求开始时间
        startTime.set(System.currentTimeMillis());
        // 请求属性
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();
        // 如果有session则返回session，如果没有则返回null(避免创建过多的session浪费内存)
        // HttpSession session = request.getSession(false);
        // 获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        StringBuffer headers = new StringBuffer();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            headers.append(name).append(":").append(value).append(",");
        }
        String uri = UUID.randomUUID() + "|" + request.getRequestURI();
        key.set(uri);

        StringBuffer params = new StringBuffer();

        String method = request.getMethod();
        // Get 请求
        if (HttpMethod.GET.toString().equals(method)) {
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                params.append(queryString);
            }
        } else {// 其他请求
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray != null && paramsArray.length > 0) {
                for (Object o : paramsArray) {
                    if (o instanceof Serializable) {
                        params.append(o.toString()).append(",");
                    } else {
                        try {
                            String param = objectMapper.writeValueAsString(o);
                            if (StringUtils.isNotBlank(param)) {
                                params.append(param).append(",");
                            }
                        } catch (JsonProcessingException e) {
                            Log.error("doBefore obj to json exception obj={},msg={}", o, e);
                        }
                    }
                }
            }
        }
        Log.info("Request Params: uri={},method={},params={},headers={}", uri, method, params, headers);
    }

    /**
     * 在方法执行后打印返回内容
     */
    @AfterReturning(returning = "obj", pointcut = "pointcut()")
    public void doAfterReturning(Object obj) {
        long costTime = System.currentTimeMillis() - startTime.get();
        String uri = key.get();
        startTime.remove();
        key.remove();
        String result = null;
        if (obj instanceof Serializable) {
            result = obj.toString();
        } else if (obj != null) {
            try {
                result = objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                Log.error("doAfter obj to json exception obj={},e={}", obj, e);
            }
        }
        Log.info("Response Result: uri={},result={},costTime={}ms", uri, result, costTime);
    }
}
