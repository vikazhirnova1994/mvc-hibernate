package com.project.service;

import com.project.dao.CustomerDao;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Customer get(int id) {
        return customerDao.get(id);
    }

    @Override
    public void delete(int id) {
        customerDao.delete(id);

    }
}
