package com.project.dao;

import com.project.domain.Customer;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface DAO<T>{
    public List<T> getAll();

    public void save(T t);

    public T get(int id);

    public void delete(int id);
}
