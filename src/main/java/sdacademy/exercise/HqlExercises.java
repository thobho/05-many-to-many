package sdacademy.exercise;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HqlExercises {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

//            Query<Car> query = session.createQuery(
//                    "from Car c where c.engineVolume > 1000 and c.brand like 'M%'",
//                    Car.class
//            );
//
//            List<Car> list = query.list();
//            System.out.println(list);


//            Query query = session.createQuery(
//                    "from Person p join p.cars c"
//            );
//
//            List list = query.list();
//
//            for (Object personCarPair : list) {
//                Object[] pair = (Object[]) personCarPair;
//
//                Person p = (Person) pair[0];
//                Car c = (Car) pair[1];
//
//                System.out.println(p.getSecondName() + " " + c.getBrand());
//
//            });

            Query<String> query = session.createQuery(
                    "select p.secondName" +
                            " from Person p join p.cars c" +
                            " where c.engineVolume > 2000" +
                            " order by c.engineVolume",
                    String.class
            );

            List<String> list = query.list();

            System.out.println(list);

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
