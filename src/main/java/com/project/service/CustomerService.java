package com.project.service;

import com.project.dao.IDao;
import com.project.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */
@Service
@Transactional
public class CustomerService implements IService<Customer, Long>{

    @Autowired
    private IDao<Customer, Long> customerDAO;

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return customerDAO.get(id);
    }

    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

}
