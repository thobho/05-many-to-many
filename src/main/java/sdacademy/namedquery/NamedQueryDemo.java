package sdacademy.namedquery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sdacademy.SessionFactoryUtils;
import sdacademy.demo.entity.VehicleOnLine;

import java.util.List;

public class NamedQueryDemo {

    public static void main(String[] args) {

        try (Session session = SessionFactoryUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(new VehicleOnLine("Aaaaa"));

            Query<VehicleOnLine> query = session
                    .createNamedQuery("getModelsStartedWithA", VehicleOnLine.class);

            query.setParameter("param", "B");

            query.list()
                    .forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

}
