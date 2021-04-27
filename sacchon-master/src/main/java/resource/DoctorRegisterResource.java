package resource;

import jpautil.JpaUtil;
import model.Doctor;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.DoctorRepository;
import representation.DoctorRepresentation;

import javax.persistence.EntityManager;

public class DoctorRegisterResource extends ServerResource {

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

    @Post("json")
    public ApiResult<DoctorRepresentation> registerDoctor(DoctorRepresentation doctorRepresentation) {
        if (doctorRepresentation == null)
            return new ApiResult<>(null, 400, "No input data to create the doctor");
        if (doctorRepresentation.getUsername() == null)
            return new ApiResult<>(null, 400, "No username was given to create the doctor");
        if (usernameExists(doctorRepresentation.getUsername()))
            return new ApiResult<>(null, 400, "Duplicate username");

        Doctor doctor = doctorRepresentation.createDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctorRepository.save(doctor);
        return new ApiResult<>(new DoctorRepresentation(doctor), 200,
                "The doctors was successfully created");
    }

    private boolean usernameExists(String candidateUsername) {
        EntityManager em = JpaUtil.getEntityManager();
        Doctor c = null;
        try {
            c = em.createQuery("SELECT u from Doctor u where u.username= :candidate", Doctor.class)
                    .setParameter("candidate", candidateUsername)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return c != null;
    }
}
