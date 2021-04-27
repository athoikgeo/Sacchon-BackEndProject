package model;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class Consultation {

    /**
     * The identification of the consultation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultationId;

    /**
     * The medication of the consultation.
     */
    private String medication;

    /**
     * The dosage of the consultation.
     */
    private float dosage;

    /**
     * The date of the consultation.
     */
    private Date date;

    /**
     * The doctorId of the consultation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctorId;

    /**
     * The patientId of the consultation.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patientId;
}




