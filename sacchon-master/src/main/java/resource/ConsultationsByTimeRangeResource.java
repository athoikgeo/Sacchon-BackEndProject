package resource;

import jpautil.JpaUtil;
import model.Consultation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import repository.ConsultationRepository;
import representation.ConsultationRepresentation;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultationsByTimeRangeResource extends ServerResource {

    private Date fromDate;
    private Date toDate;

    @Get("json")
    public List<ConsultationRepresentation> getConsultationsByTimeRange() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        List<Consultation> consultations = consultationRepository.findConsultationsByTimeRange(fromDate, toDate);
        em.close();
        List<ConsultationRepresentation> consultationRepresentations =
                consultations.stream()
                        .map(ConsultationRepresentation::new)
                        .collect(toList());

        return consultationRepresentations;
    }
}
