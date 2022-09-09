package com.project.dao;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface IDao<T>{
    public List<T> getAll();

    public void save(T t);

    public T get(int id);

    public void delete(int id);
}
