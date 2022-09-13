package com.project.dao;

import com.project.domain.Position;

import java.util.List;
import java.util.Optional;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface IDao<T, I>{
    public List<T> getAll();

    public void save(T t);

    public T get(I id);

    public void delete(I id);

    public void update(T t);

}
