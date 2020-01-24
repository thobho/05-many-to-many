package sdacademy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.BusLine;
import sdacademy.demo.entity.Driver;
import sdacademy.demo.entity.DriverDetail;
import sdacademy.demo.entity.VehicleOnLine;
import sdacademy.exercise.Car;
import sdacademy.exercise.Person;

public class SessionFactoryUtils {
    private static SessionFactory SESSION_FACTORY = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(DriverDetail.class)
            .addAnnotatedClass(Driver.class)
            .addAnnotatedClass(BusLine.class)
            .addAnnotatedClass(VehicleOnLine.class)
            .addAnnotatedClass(Car.class)
            .addAnnotatedClass(Person.class)
            .buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    public static void close(){
        SESSION_FACTORY.close();
    }
}
