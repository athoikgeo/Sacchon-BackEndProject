package representation;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Doctor;

@Data
@NoArgsConstructor
public class DoctorRepresentation {

    /**
     * The username of the doctor.
     */
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
     * The uri of the doctor.
     */
    private String uri;

    public DoctorRepresentation(Doctor doctor) {
        if (doctor != null) {
            username = doctor.getUsername();
            firstName = doctor.getFirstName();
            lastName = doctor.getLastName();
            password = doctor.getPassword();
            role = doctor.getRole();
            uri = "http://localhost:9000/v1/doctor/" + doctor.getDoctorId();
        }
    }

    public Doctor createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setRole(role);
        return doctor;
    }
}
