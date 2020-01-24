package sdacademy.detached;

import org.hibernate.Session;
import sdacademy.SessionFactoryUtils;
import sdacademy.demo.entity.VehicleOnLine;

public class DetatchedEntityDemo {
    public static void main(String[] args) {
        saveAndUpdate();
    }

    private static void evictExample() {
        VehicleOnLine volvo = new VehicleOnLine("Volvo");

        try (Session session = SessionFactoryUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            System.out.println(session.contains(volvo));

            session.save(volvo);
            volvo.setModel("Volvo 2");
            System.out.println(session.contains(volvo));

//            session.evict(volvo);
            System.out.println(session.contains(volvo));


//encja jest śledzona i wszystkie zmiany w niej są zpisane
            session.getTransaction().commit();
        } finally {
            SessionFactoryUtils.close();
        }
    }

    private static void saveAndUpdate() {
        VehicleOnLine volvo = new VehicleOnLine("Volvo");

        try (Session session = SessionFactoryUtils.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();


            session.save(volvo);

            VehicleOnLine volvo2 = new VehicleOnLine("Volvo 2");
            volvo2.setId(volvo.getId());
            session.evict(volvo);
            session.save(volvo2); //change to save or update

            session.getTransaction().commit();
        } finally {
            SessionFactoryUtils.close();
        }
    }


    private static void mergeExample() {
        VehicleOnLine volvo = new VehicleOnLine("Volvo");
        Session session = SessionFactoryUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(volvo);
            //od etgo momentu wszystkie modyfikacje są śledzone
            volvo.setModel("Volvo 2");
            session.getTransaction().commit();
            session.close();

            volvo.setModel("Volvo 3");

            session = SessionFactoryUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(volvo);
            volvo.setModel("Volvo 4");
            volvo.setModel("Volvo 3");

            session.getTransaction().commit();
        } finally {
            session.close();
            SessionFactoryUtils.close();
        }
    }
}
