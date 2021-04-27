package resource;

import jpautil.JpaUtil;
import model.Patient;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;

public class PatientRegisterResource extends ServerResource {

    @Post("json")
    public ApiResult<PatientRepresentation> registerPatient(PatientRepresentation patientRepresentation) {
        if (patientRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the patient");
        if (patientRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username was given to create the patient");
        if (usernameExists(patientRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");

        Patient patient = patientRepresentation.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        return new ApiResult<>(new PatientRepresentation(patient), 200,
                "The patients was successfully created");
    }

    public boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Patient c = null;
        try {
            c = em.createQuery("SELECT u from Patient u where u.username= :candidate", Patient.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return c != null;
    }

    @Get
    public boolean usernameExists() {
        String candidateUsername = "";

        try {
            candidateUsername = getQueryValue("username");
        } catch (Exception e) {
            return false;
        }
        return usernameExists(candidateUsername);
    }
}
