package com.project.dao;

import com.project.domain.Customer;
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
public class PositionDao implements IDao<Position, Long> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Position> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Position> cq = cb.createQuery(Position.class);
        Root<Position> root = cq.from(Position.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void save(Position position) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(position);
    }

    @Override
    public Position get(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Position position = currentSession.get(Position.class, id);
        return position;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Position position = session.byId(Position.class).load(id);
        session.delete(position);
    }

    @Override
    public void update(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.update(position);
    }

    public Optional<Position> getByNamePosition(String position){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createNamedQuery("Position_FindByNamePosition", Position.class);
        query.setParameter("position", position);
        return query.getResultList()
                .stream()
                .findFirst();
    }
}
