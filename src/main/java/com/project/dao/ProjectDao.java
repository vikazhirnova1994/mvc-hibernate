package com.project.dao;

import com.project.domain.Employee;
import com.project.domain.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Repository
public class ProjectDao implements DAO<Project>{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        Root<Project> root = cq.from(Project.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(Project project) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(project);
    }

    @Override
    public Project get(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Project project = currentSession.get(Project.class, id);
        return project;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.byId(Project.class).load(id);
        session.delete(project);
    }
}
