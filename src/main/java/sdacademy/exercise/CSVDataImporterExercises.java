package sdacademy.exercise;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class CSVDataImporterExercises {

    private List<Car> getRandomCars(List<Car> cars, int max) {
        Collections.shuffle(cars);
        return cars.subList(0, new Random().nextInt(max));
    }

    private List<Person> loadPersons() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("persons.csv"));
        List<Person> people = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] personRecord = scanner.nextLine().split(",");
            int year = Integer.parseInt(personRecord[4]);
            int month = Integer.parseInt(personRecord[3]);
            int day = Integer.parseInt(personRecord[2]);
            LocalDate date = LocalDate.of(year, month, day);
            Person newPerson = new Person(personRecord[0], personRecord[1], date);
            people.add(newPerson);
        }
        return people;
    }

    private List<Car> loadCars() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("cars.csv"));
        List<Car> cars = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] carRecord = scanner.nextLine().split(",");
            Car newCar = new Car(
                    carRecord[0],
                    Double.parseDouble(carRecord[1]),
                    carRecord[2]
            );
            cars.add(newCar);
        }
        return cars;

    }

    public static void main(String[] args) throws FileNotFoundException {
        int MAX_CAR_PER_PERSON = 3;


        CSVDataImporterExercises csvDataImporter = new CSVDataImporterExercises();
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();


            List<Car> cars = csvDataImporter.loadCars();
            List<Person> people = csvDataImporter.loadPersons();


            cars.forEach(car -> {
                session.save(car);
            });


            people.forEach(person -> {
                session.save(person);
            });


            people.forEach(person -> {
                List<Car> randomCars = csvDataImporter.getRandomCars(cars, MAX_CAR_PER_PERSON);
                person.setCars(randomCars);
            });




            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
