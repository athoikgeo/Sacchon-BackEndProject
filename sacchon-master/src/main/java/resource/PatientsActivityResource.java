package resource;

import jpautil.JpaUtil;
import model.Patient;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import repository.PatientRepository;
import representation.PatientRepresentation;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientsActivityResource extends ServerResource {

    private Date startDate;
    private Date endDate;

    @Get("json")
    public List<PatientRepresentation> getPatients() {

        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        List<Patient> inactivePatients = patientRepository.findPatientsActivity(startDate, endDate);
        em.close();

        List<PatientRepresentation> patientRepresentationList =
                inactivePatients.stream()
                        .map(PatientRepresentation::new)
                        .collect(toList());

        return patientRepresentationList;
    }
}
