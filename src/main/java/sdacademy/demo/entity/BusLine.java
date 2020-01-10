package sdacademy.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bus_line")
public class BusLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_line_id")
    private List<VehicleOnLine> vehicleOnLineList;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            })
    @JoinTable(name = "bus_line_passenger",
            joinColumns = @JoinColumn(name = "bus_line_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengers;

    public BusLine() {
    }

    public BusLine(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<VehicleOnLine> getVehicleOnLineList() {
        return vehicleOnLineList;
    }

    public void setVehicleOnLineList(List<VehicleOnLine> vehicleOnLineList) {
        this.vehicleOnLineList = vehicleOnLineList;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * Dodaj pojazd jeżdżący po linii
     *
     * @param vehicleOnLine dodawany pojazd
     */
    public void addVehicle(VehicleOnLine vehicleOnLine) {
        if (vehicleOnLineList == null) {
            vehicleOnLineList = new ArrayList<>();
        }
        vehicleOnLineList.add(vehicleOnLine);
    }

    /**
     * Dodaj pasażera jeżdżącego po linii
     *
     * @param passenger dodawany pasażer
     */
    public void addPassenger(Passenger passenger) {
        if (passengers == null) {
            passengers = new ArrayList<>();
        }
        passengers.add(passenger);
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
