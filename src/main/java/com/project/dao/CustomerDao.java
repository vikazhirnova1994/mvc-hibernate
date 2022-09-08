package com.project.dao;

import com.project.domain.Customer;

import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public interface CustomerDao {
    public List<Customer> getAll();

    public void save(Customer customer);

    public Customer get(int id);

    public void delete(int id);
}
