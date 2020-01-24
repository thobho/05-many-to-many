package sdacademy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sdacademy.SessionFactoryUtils;
import sdacademy.demo.entity.VehicleOnLine;

public class VehicleOnLineDAO {
    private Session currentSession;

    private Transaction currentTransaction;

    public VehicleOnLineDAO() {
    }

    public Session openCurrentSession() {
        currentSession = SessionFactoryUtils.getSessionFactory().openSession();
        return currentSession;
    }

    private Session getCurrentSession() {
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = SessionFactoryUtils.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public void persist(VehicleOnLine entity) {
        getCurrentSession().save(entity);
    }

    public void update(VehicleOnLine entity) {
        getCurrentSession().update(entity);
    }

    public VehicleOnLine findById(String id) {
        return getCurrentSession().get(VehicleOnLine.class, id);
    }

    public void delete(VehicleOnLine entity) {
        getCurrentSession().delete(entity);
    }
}
