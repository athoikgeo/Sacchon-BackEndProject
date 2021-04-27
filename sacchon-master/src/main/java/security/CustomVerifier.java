package security;

import jpautil.JpaUtil;
import model.Doctor;
import model.Patient;
import model.Reporter;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;
import repository.DoctorRepository;
import repository.PatientRepository;
import repository.ReporterRepository;

import javax.persistence.EntityManager;

public class CustomVerifier extends SecretVerifier {

    @Override
    public int verify(String username, char[] password) {
        EntityManager em = JpaUtil.getEntityManager();

        DoctorRepository doctorRepository = new DoctorRepository(em);

        Doctor doctor = doctorRepository.getByUsername(username);
        if (doctor == null)
            return SecretVerifier.RESULT_INVALID;

        if (compare(doctor.getPassword().toCharArray(), password)) {
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role(doctor.getRole()));
            return SecretVerifier.RESULT_VALID;
        }

        PatientRepository patientRepository = new PatientRepository(em);

        Patient patient = patientRepository.getByUsername(username);
        if (patient == null)
            return SecretVerifier.RESULT_INVALID;

        if (compare(patient.getPassword().toCharArray(), password)) {
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role(patient.getRole()));
            return SecretVerifier.RESULT_VALID;
        }

        ReporterRepository reporterRepository = new ReporterRepository(em);

        Reporter reporter = reporterRepository.getByUsername(username);
        if (reporter == null)
            return SecretVerifier.RESULT_INVALID;

        if (compare(reporter.getPassword().toCharArray(), password)) {
            Request request = Request.getCurrent();
            request.getClientInfo().getRoles().add
                    (new Role(reporter.getRole()));
            return SecretVerifier.RESULT_VALID;
        }
        return SecretVerifier.RESULT_INVALID;
    }
}