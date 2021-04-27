package representation;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Measurement;

import java.util.Date;

@Data
@NoArgsConstructor
public class MeasurementRepresentation {

    /**
     * The identification of the measurement.
     */
    private int measurementId;

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
     * The identification of the patient.
     */
    private int patientId;

    /**
     * The uri of the measurement.
     */
    private String uri;

    public MeasurementRepresentation(Measurement measurement) {
        if (measurement != null) {
            measurementId = measurement.getMeasurementId();
            carbIn = measurement.getCarbIn();
            glucoseLevel = measurement.getGlucoseLevel();
            date = measurement.getDate();
            if (measurement.getPatientId() != null) {
                patientId = measurement.getPatientId().getPatientId();
            }
            uri = "http://localhost:9000/v1/measurement/" + measurement.getMeasurementId();
        }
    }

    public Measurement createMeasurement() {
        Measurement measurement = new Measurement();
        measurement.setMeasurementId(measurementId);
        measurement.setGlucoseLevel(glucoseLevel);
        measurement.setCarbIn(carbIn);
        measurement.setDate(date);
        return measurement;
    }
}