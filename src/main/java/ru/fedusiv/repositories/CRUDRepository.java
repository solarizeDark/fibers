package ru.fedusiv.repositories;

import java.util.List;

public interface CRUDRepository<T> {

    void save(T entity);
    void update(T entity);

    T findById(Long id);
    List<T> findAll();

}
