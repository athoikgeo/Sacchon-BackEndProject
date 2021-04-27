package model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Measurement {

    /**
     * The identification of the measurement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int measurementId;

    /**
     * The date of the measurement.
     */
    private Date date;

    /**
     * The carbIn of the measurement.
     */
    private float carbIn;

    /**
     * The glucoseLevel of the measurement.
     */
    private float glucoseLevel;

    /**
     * The measurement of the patient.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patientId;

}
