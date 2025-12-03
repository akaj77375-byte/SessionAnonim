package service;

import java.util.List;

public  interface GenericService<T> {
    void create(T t);

    List<T> getAll();

    T getById(Long id);

    void update(Long id, T t);

    void delete(Long id);
}

