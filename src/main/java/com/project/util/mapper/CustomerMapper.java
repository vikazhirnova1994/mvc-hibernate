package com.project.util.mapper;

import com.project.domain.Customer;
import com.project.util.form.CustomerForm;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

public final class CustomerMapper {
    public static Customer customerFormToEntity(CustomerForm customerForm) {
        Customer customer = new Customer();
        customer.setCustomerId(customerForm.getCustomerId());
        customer.setName(customerForm.getName());
        customer.setEmail(customerForm.getEmail());
        return customer;
    }

    public static CustomerForm entityToCustomerForm(Customer entity) {
        CustomerForm customerFormModel = new CustomerForm();
       customerFormModel.setCustomerId(entity.getCustomerId());
        customerFormModel.setName(entity.getName());
        customerFormModel.setEmail(entity.getEmail());
        return customerFormModel;
    }
}
