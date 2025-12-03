package dao;

import java.util.List;

public interface GenericDao<T> {
    void create(T t);

    List<T> getAll();

    T getById(Long id);

    void update(Long id, T t);

    void delete(Long id);}
