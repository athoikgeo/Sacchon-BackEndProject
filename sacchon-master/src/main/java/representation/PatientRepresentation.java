package representation;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Patient;

@Data
@NoArgsConstructor
public class PatientRepresentation {

    /**
     * The identification of the patient.
     */
    private int patientId;

    /**
     * The username of the patient.
     */
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
     * The uri of the patient.
     */
    private String uri;

    public PatientRepresentation(Patient patient) {
        if (patient != null) {
            patientId = patient.getPatientId();
            username = patient.getUsername();
            password = patient.getPassword();
            firstName = patient.getFirstName();
            lastName = patient.getLastName();
            role = patient.getRole();
            uri = "http://localhost:9000/v1/patient/" + patient.getPatientId();
        }
    }

    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setUsername(username);
        patient.setPassword(password);
        patient.setRole(role);
        return patient;
    }

}