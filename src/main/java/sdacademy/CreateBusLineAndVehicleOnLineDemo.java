package sdacademy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sdacademy.demo.entity.BusLine;
import sdacademy.demo.entity.Driver;
import sdacademy.demo.entity.DriverDetail;
import sdacademy.demo.entity.VehicleOnLine;

public class CreateBusLineAndVehicleOnLineDemo {

    public static void main(String[] args) {

        Session session = SessionFactoryUtils.getSessionFactory().getCurrentSession();

        try {
            //rozpocznij transakcję żeby zapisać
            session.beginTransaction();

            //stwórz BusLine
            BusLine busLine = new BusLine("Linia nr 1");

            //dodaj pojazdy na trasie
            VehicleOnLine volvo = new VehicleOnLine("Volvo");
            VehicleOnLine mercedes = new VehicleOnLine("Mercedes");

            busLine.addVehicle(volvo);
            busLine.addVehicle(mercedes);

            //zapisz BusLine, zapiszą się również pojazdy
            session.save(busLine);

            //zakomituj transakcję
            session.getTransaction().commit();

        } finally {
            //posprzątaj po otwartej sesji
            session.close();
            SessionFactoryUtils.close();
        }
    }

}
