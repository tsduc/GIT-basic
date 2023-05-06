package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.BuildingEntity;

import java.util.List;

public interface JdbcRepositoryCustom<T> {
    List<T> findByCondition(String sql);

    T findId(Long id);

    Long insert(Object object);

    void delete(Long id);

    void update(Object object, Long id);

    void deleteProperty(String where);
}
