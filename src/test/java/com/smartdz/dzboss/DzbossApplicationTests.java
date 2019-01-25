package com.smartdz.dzboss;

import com.smartdz.dzboss.domain.User;
import com.smartdz.dzboss.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DzbossApplicationTests {
    private static final Logger Log = LoggerFactory.getLogger(DzbossApplicationTests.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        // stringRedisTemplate.opsForValue().set("test", "测试");
        System.out.println(stringRedisTemplate.opsForValue().get("test"));
    }

    @Test
    public void findById() {
        long time = System.currentTimeMillis();
        userService.findById(1L);
        Log.info("耗时：{}毫秒", (System.currentTimeMillis() - time));
    }
}

