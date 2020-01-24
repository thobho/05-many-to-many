package sdacademy.exercise;

import javassist.expr.Expr;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sdacademy.SessionFactoryUtils;

import javax.persistence.criteria.*;
import java.util.List;

public class CarPersonDataAccess {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

//            Query query = session.createQuery(
//                    "select person.secondName " +
//                            "from sdacademy.exercise.Person as person join person.cars " +
//                            "where person.firstName like 'A%'",
//                    String.class);
//            Query query = session.createQuery("from sdacademy.exercise.Person as person join person.cars where person.firstName like 'A%'");
//            Query query = session.createQuery("select distinct person.firstName from sdacademy.exercise.Person as person join person.cars where person.firstName like 'A%'");
//            Query query = session.createQuery("from sdacademy.exercise.Car car where car.engineVolume > 1500 order by car.engineVolume desc ", Car.class);
            Query query = session.createQuery("from sdacademy.exercise.Car car where car.engineVolume > 1500 order by car.engineVolume desc ", Car.class);

//            List list = query.list();
//            System.out.println(list);

//            NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM car WHERE ENGINE_VOLUME > 1000 AND ENGINE_VOLUME < 2000");
//            List list = sqlQuery.list();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);

            Root<Car> root = criteriaQuery.from(Car.class);

//            criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("engineVolume"), 1000));
//            criteriaQuery.select(root).where(criteriaBuilder.like(root.get("brand"), "Ope%"));
//            criteriaQuery.select(root).where(criteriaBuilder.between(root.get("engineVolume"), 1000, 2000));
//
//            Predicate or = criteriaBuilder.or(
//                    criteriaBuilder.gt(root.get("engineVolume"), 1000),
//                    criteriaBuilder.like(root.get("brand"), "Ope%")
//            );
//            criteriaQuery.select(root).where(or);



            criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("engineVolume")));

            List<Car> resultList = session.createQuery(criteriaQuery).getResultList();



            CriteriaQuery<Double> projectionQuery = criteriaBuilder.createQuery(Double.class);
            Root<Car> from = projectionQuery.from(Car.class);
            projectionQuery.select(criteriaBuilder.avg(from.get("engineVolume")));

            Double singleResult = session.createQuery(projectionQuery).getSingleResult();

            System.out.println(singleResult);


            session.getTransaction().commit();
        } finally {
            SessionFactoryUtils.close();
        }

    }

}
