package representation;


import lombok.Data;
import lombok.NoArgsConstructor;
import model.Patient;
import model.Reporter;

@Data
@NoArgsConstructor
public class ReporterRepresentation {

    /**
     * The identification of the reporter.
     */
    private int reporterId;

    /**
     * The username of the reporter.
     */
    private String username;

    /**
     * The password of the reporter.
     */
    private String password;

    /**
     * The firstName of the reporter.
     */
    private String firstName;

    /**
     * The lastName of the reporter.
     */
    private String lastName;

    /**
     * The role of the reporter.
     */
    private String role;

    /**
     * The uri of the reporter.
     */
    private String uri;

    public ReporterRepresentation( Reporter reporter) {
        if (reporter != null) {
            username= reporter.getUsername();
            firstName = reporter.getFirstName();
            lastName = reporter.getLastName();
            password = reporter.getPassword();
            role = reporter.getRole();
            uri = "http://localhost:9000/v1/reporter/" + reporter.getReporterId();
        }
    }

    public Reporter createReporter() {
        Reporter reporter = new Reporter();
        reporter.setFirstName(firstName);
        reporter.setLastName(lastName);
        reporter.setUsername(username);
        reporter.setPassword(password);
        reporter.setRole(role);
        return reporter;
    }

}
