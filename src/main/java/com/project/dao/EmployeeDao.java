package com.project.dao;

import com.project.domain.Employee;
import com.project.domain.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class EmployeeDao implements IDao<Employee, Long> {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(Employee employee) {

        //добавить в ui выбр позиции, передать через Model
      //  Position position = new Position();
      //  employee.setPosition(position);

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public Employee get(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Employee employee = currentSession.get(Employee.class, id);
        return employee;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.byId(Employee.class).load(id);
        session.delete(employee);
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }
    public Optional<Employee> getEmployeeByPosition(Position position){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("Employee_FindByPosition", Employee.class);
        query.setParameter("position", position);
        return query.getResultList()
                .stream()
                .findFirst();
    }
}
