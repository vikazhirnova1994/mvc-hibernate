package com.project.service;

import com.project.dao.CustomerDao;
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
public class CustService  implements IService<CustomerForm, Long> {

    @Autowired
    private CustomerDao customerDao;


    @Override
    public List<CustomerForm> getAll() {
        return   customerDao.getAll()
                .stream()
                .map(CustomerMapper::entityToCustomerForm)
                .collect(Collectors.toList());
    }
    @Override
    public void save(CustomerForm customerForm) {
        Customer customer = CustomerMapper.customerFormToEntity(customerForm);
        customerDao.save(customer);
    }

    @Override
    public CustomerForm get(Long id) {
        return  CustomerMapper.entityToCustomerForm(customerDao.get(id));
    }

    @Override
    public void delete(Long id) {
        customerDao.delete(id);
    }

    @Override
    public void update(CustomerForm customerForm) {
        Customer customer = CustomerMapper.customerFormToEntity(customerForm);
        customerDao.update(customer);
    }
}
