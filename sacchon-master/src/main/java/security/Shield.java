package security;

import org.restlet.Application;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Verifier;

public class Shield {

    /**
     * The role of the admin.
     */
    public static final String ROLE_REPORTER = "reporter";

    /**
     * The role of the doctor.
     */
    public static final String ROLE_DOCTOR = "doctor";

    /**
     * The role of the patient.
     */
    public static final String ROLE_PATIENT = "patient";

    private Application application;

    public Shield(Application application) {
        this.application = application;
    }

    public ChallengeAuthenticator createApiGuard() {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
                application.getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        Verifier verifier = new CustomVerifier();
        apiGuard.setVerifier(verifier);

        return apiGuard;
    }
}