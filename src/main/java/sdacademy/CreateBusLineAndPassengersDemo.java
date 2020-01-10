package sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.*;

public class CreateBusLineAndPassengersDemo {

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

            //stwórz i zapisz BusLine
            BusLine busLine = new BusLine("Linia nr 1");
            System.out.println("----Zapisuję BusLine----");
            session.save(busLine);

            //stwórz i zapisz Passenger
            Passenger passenger1 = new Passenger("Jan", "Kowalski");
            Passenger passenger2 = new Passenger("Maria", "Brzechwa");

            //dodaj pasażerów do linii
            busLine.addPassenger(passenger1);
            busLine.addPassenger(passenger2);

            //zapisz Passenger
            System.out.println("----Zapisuję Pasażerów----");
            session.save(passenger1);
            session.save(passenger2);
            System.out.println("Zapisani pasażerowie: " + busLine.getPassengers());

            //zakomituj transakcję
            session.getTransaction().commit();

        } finally {
            //posprzątaj po otwartej sesji
            session.close();
            factory.close();
        }
    }

}
