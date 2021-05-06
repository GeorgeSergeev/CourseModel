package ru.khrebtov.university.service;

import java.util.List;

public interface AbstractService<T> {
    List<T> findAll();

    T findById(Long id);

    Long count();

    void insert(T entity);

    void update(T entity);

    void deleteById(Long id);

    void saveOrUpdate(T entity);
}
