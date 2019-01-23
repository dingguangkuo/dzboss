//package com.smartdz.dzboss.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class MyInterceptor {
//
//    @Pointcut("within (com.smartdz.dzboss.controller..*)")
//    public void pointCut() {
//    }
//
//    @Around("pointCut()")
//    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attributes != null) {
//            HttpServletRequest request = attributes.getRequest();
//            User user = (User) request.getSession().getAttribute("user");
//            if (user == null) {
//                attributes.getResponse().sendError(422, "token error");
//            }
//        }
//        return joinPoint.proceed();
//    }
//}