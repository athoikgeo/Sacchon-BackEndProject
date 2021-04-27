package resource;

import jpautil.JpaUtil;
import model.Patient;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientListResource extends ServerResource {

    @Get("json")
    public List<PatientRepresentation> getPatients() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        List<Patient> patients = patientRepository.findAll();
        em.close();
        return patients.stream()
                .map(PatientRepresentation::new)
                .collect(toList());
    }

    @Post("json")
    public PatientRepresentation add(PatientRepresentation patientRepresentationIn) {

        if (patientRepresentationIn == null) return null;
        if (patientRepresentationIn.getFirstName() == null) return null;
        if (patientRepresentationIn.getLastName() == null) return null;
        if (patientRepresentationIn.getPassword() == null) return null;
        if (patientRepresentationIn.getUsername() == null) return null;
        if (patientRepresentationIn.getRole() == null) return null;

        Patient patient = patientRepresentationIn.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return patientRepresentationIn;
    }
}
