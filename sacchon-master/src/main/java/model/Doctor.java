package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Doctor {

    /**
     * The identification of the doctor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    /**
     * The username of the doctor.
     */
    @Column(unique=true)
    private String username;

    /**
     * The password of the doctor.
     */
    private String password;

    /**
     * The firstName of the doctor.
     */
    private String firstName;

    /**
     * The lastName of the doctor.
     */
    private String lastName;

    /**
     * The role of the doctor.
     */
    private String role;

    /**
     * The consultations of the doctor.
     */
    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;

}
