package com.smartdz.dzboss.service;

import java.util.List;

/**
 * 基本CRUD方法的编写
 *
 * @param <T>
 */
public interface BaseService<T> {
    List<T> findAll();

    T findById(Long id);

    void create(T t);

    void delete(Long... ids);

    void update(T t);
}
