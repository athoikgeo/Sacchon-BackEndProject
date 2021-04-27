package representation;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Consultation;

import java.util.Date;

@Data
@NoArgsConstructor
public class ConsultationRepresentation {

    /**
     * The identification of the consultation.
     */
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
     * The identification of the doctor.
     */
    private int doctorId;

    /**
     * The identification of the patient.
     */
    private int patientId;

    /**
     * The uri of the consultation.
     */
    private String uri;

    public ConsultationRepresentation(Consultation consultation) {
        if (consultation != null) {
            consultationId = consultation.getConsultationId();
            medication = consultation.getMedication();
            dosage = consultation.getDosage();
            doctorId = consultation.getDoctorId().getDoctorId();
            patientId = consultation.getPatientId().getPatientId();
            date = consultation.getDate();
            uri = "http://localhost:9000/v1/consultation/" + consultation.getConsultationId();
        }
    }

    public Consultation createConsultation() {
        Consultation consultation = new Consultation();
        consultation.setConsultationId(consultationId);
        consultation.setMedication(medication);
        consultation.setDosage(dosage);
        consultation.setDate(date);
        return consultation;
    }
}