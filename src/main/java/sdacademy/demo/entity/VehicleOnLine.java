package sdacademy.demo.entity;

import javax.persistence.*;
import org.hibernate.annotations.NamedQuery;
@Entity
@Table(name = "vehicle_on_line")
@NamedQuery(
        name = "getModelsStartedWithA",
        query = "from VehicleOnLine vol where vol.model like ':param%'"
)
public class VehicleOnLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    public VehicleOnLine() {
    }

    public VehicleOnLine(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "VehicleOnLine{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
