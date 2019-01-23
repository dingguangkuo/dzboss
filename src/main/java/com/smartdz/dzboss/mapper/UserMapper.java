package com.smartdz.dzboss.mapper;

import com.smartdz.dzboss.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User findById(Long id);
}
