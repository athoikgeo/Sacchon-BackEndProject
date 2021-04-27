package resource;

import jpautil.JpaUtil;
import model.Consultation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import repository.ConsultationRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientConsultationsResource extends ServerResource {

    private int patientId;

    @Override
    protected void doInit() {
        patientId = Integer.parseInt(getAttribute("patientId"));
    }

    @Get("json")
    public List<ConsultationRepresentation> getPatientsConsultations() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        List<Consultation> consultations = consultationRepository.findPatientsConsultations(patientId);
        em.close();
        return consultations.stream()
                .map(ConsultationRepresentation::new)
                .collect(toList());
    }
}
