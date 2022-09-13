package com.project.service;

import com.project.dao.IDao;
import com.project.domain.Customer;
import com.project.util.form.CustomerForm;
import com.project.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */
@Service
@Transactional
public class CustomerService implements IService<CustomerForm, Long>{

    @Autowired
    private IDao<Customer, Long> customerDAO;
    @Override
    public List<CustomerForm> getAll() {
        return customerDAO.getAll()
                .stream()
                .map(CustomerMapper::entityToCustomerForm)
                .collect(Collectors.toList());
    }
    @Override
    public void save(CustomerForm customer) {
        customerDAO.save(
                CustomerMapper.customerFormToEntity(customer));
    }
    @Override
    public CustomerForm get(Long id) {
        return CustomerMapper.entityToCustomerForm(
                      customerDAO.get(id));
    }
    @Override
    public void delete(Long id) {
        customerDAO.delete(id);
    }

    @Override
    public void update(CustomerForm customer) {
        customerDAO.update(CustomerMapper.customerFormToEntity(customer));
    }
}
