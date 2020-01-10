package sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.*;

public class AddBusLineForPassengerDemo {

    public static void main(String[] args) {
        //tworzenie fabryki
        // dodanie konfiguracji, dodanie klas
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(DriverDetail.class)
                .addAnnotatedClass(Driver.class)
                .addAnnotatedClass(BusLine.class)
                .addAnnotatedClass(VehicleOnLine.class)
                .addAnnotatedClass(Passenger.class)
                .buildSessionFactory();
        //tworzenie sesji na potrzeby naszej pracy
        Session session = factory.getCurrentSession();

        try {
            //rozpocznij transakcję żeby zapisać
            session.beginTransaction();

            //pobierz pasażera id = 1
            Long id = 1L;
            Passenger passenger = session.get(Passenger.class, id);
            System.out.println("Pobrany pasażer: " + passenger);
            System.out.println("Pobrane linie dla pasażera: " + passenger.getBusLines());

            //stwórz nową linię
            BusLine busLine1 = new BusLine("Linia 33");
            BusLine busLine2 = new BusLine("Linia 15");

            //dodaj pasażera do nowej linii
            busLine1.addPassenger(passenger);
            busLine2.addPassenger(passenger);

            //zapisz linię
            session.save(busLine1);
            session.save(busLine2);

            //zakomituj transakcję
            session.getTransaction().commit();

        } finally {
            //posprzątaj po otwartej sesji
            session.close();
            factory.close();
        }
    }

}
