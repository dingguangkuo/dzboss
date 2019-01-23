package com.smartdz.dzboss;

import com.smartdz.dzboss.domain.User;
import com.smartdz.dzboss.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DzbossApplicationTests {
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
        User user = userService.findById(1L);
        System.out.println(user);
    }
}

