package sdacademy.exercise;



import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car", indexes = {})
public class Car {
    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @Column(name = "engine_volume", nullable = false, scale = 6, precision = 2)
    private Double engineVolume;

    @Column(name = "car_brand")
    private String brand;

    @ManyToMany(mappedBy = "cars")
    private List<Person> persons;

    private Integer[] test;


    public Car() {
    }

    public Car(String registrationNumber, Double engineVolume, String brand) {
        this.registrationNumber = registrationNumber;
        this.engineVolume = engineVolume;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", engineVolume=" + engineVolume +
                ", brand='" + brand + '\'' +
                '}';
    }

    public Integer[] getTest() {
        return test;
    }

    public void setTest(Integer[] test) {
        this.test = test;
    }
}
