package com.smartdz.dzboss.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smartdz.dzboss.domain.BaseListMdl;
import com.smartdz.dzboss.domain.User;
import com.smartdz.dzboss.mapper.UserMapper;
import com.smartdz.dzboss.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseListMdl<User> findByPage(int pageNum, int pageSize) {
        // 使用 Mybatis 分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> all = userMapper.findAll();
        // 将查询到的 list 放入 PageInfo 类中
        PageInfo<User> pageInfo = new PageInfo<>(all);

        return new BaseListMdl<>(pageInfo.getPageNum(), pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    @Cacheable(value = "user", key = "'id_' + #id")
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void create(User user) {
    }

    @Override
    public void delete(Long... ids) {
    }

    @Override
    public void update(User user) {
    }
}
