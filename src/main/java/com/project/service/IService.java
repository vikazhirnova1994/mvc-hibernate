package com.project.service;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface IService<T, I>{
    public List<T> getAll();

    public void save(T t);

    public T get(I id);

    public void delete(I id);

    public void update(T t);
}
