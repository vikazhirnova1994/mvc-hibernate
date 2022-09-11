package com.project.dao;

import com.project.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Repository
public class CustomerDao implements IDao<Customer, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer get(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = currentSession.get(Customer.class, id);
        return customer;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.byId(Customer.class).load(id);
        session.delete(customer);
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customer);
    }

    public Optional<Customer> getByName(String name){
       Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("Customer_FindByName", Customer.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }
}
