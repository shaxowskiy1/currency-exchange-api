package ru.shaxowskiy.springlesson.repositories;

import java.util.List;

public interface CrudRepository <T, ID> {

    T findById(ID id);

    T findByName(String name);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(ID id);

}