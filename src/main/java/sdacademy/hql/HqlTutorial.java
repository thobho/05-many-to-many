package sdacademy.hql;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sdacademy.SessionFactoryUtils;
import sdacademy.demo.entity.VehicleOnLine;

import java.util.List;

public class HqlTutorial {

    public static void main(String[] args) {
        try (Session session = SessionFactoryUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query query = session.createQuery(
                    "from sdacademy.exercise.Person"
            );

            List resultList = query.getResultList();

            session.getTransaction().commit();
        }
    }

    private static void delete(Session session) {

        Query query = session.createQuery("delete from sdacademy.demo.entity.VehicleOnLine where id = :id");
        query.setParameter("id", 10L);
        query.executeUpdate();
        VehicleOnLine get= session.get(VehicleOnLine.class, 10L);
        System.out.println(get);

    }

    private static void update(Session session) {

        Query query = session.createQuery("update sdacademy.demo.entity.VehicleOnLine set model = :name where id = :id");
        query.setParameter("name", "Skoda");
        query.setParameter("id", 4L);
        query.executeUpdate();

        VehicleOnLine load = session.load(VehicleOnLine.class, 4L);
        System.out.println(load);

    }

    private static void select(Session session) {
        Query query = session.createQuery("from sdacademy.demo.entity.VehicleOnLine vol where vol.id > 100");
        query.getResultList().forEach(System.out::println);

        Query query2 = session.createQuery("from sdacademy.demo.entity.VehicleOnLine vol where vol.id > :minId");
        query2.setParameter("minId", 10L);
        query2.getResultList().forEach(System.out::println);
    }
}
