package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Reporter {

    /**
     * The identification of the reporter.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}
