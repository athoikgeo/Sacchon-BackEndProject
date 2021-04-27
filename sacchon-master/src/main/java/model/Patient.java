package model;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Patient {

    /**
     * The identification of the patient.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    /**
     * The identification of the patient.
     */
    @Column(unique=true)
    private String username;

    /**
     * The password of the patient.
     */
    private String password;

    /**
     * The firstName of the patient.
     */
    private String firstName;

    /**
     * The lastName of the patient.
     */
    private String lastName;

    /**
     * The role of the patient.
     */
    private String role;

    /**
     * The measurements of the patient.
     */
    @OneToMany(mappedBy = "patientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurements;;

    /**
     * The consultations of the patient.
     */
    @OneToMany(mappedBy = "patientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;
}