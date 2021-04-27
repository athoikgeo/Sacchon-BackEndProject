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

public class DoctorsActivityResource extends ServerResource {

    private Date startDate;
    private Date endDate;

    @Get("json")
    public List<ConsultationRepresentation> getConsultations() {

        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);

        List<Consultation> inactiveConsultations = consultationRepository.findDoctorsActivity(startDate, endDate);
        em.close();

        List<ConsultationRepresentation> consultationRepresentationList =
                inactiveConsultations.stream()
                        .map(ConsultationRepresentation::new)
                        .collect(toList());

        return consultationRepresentationList;
    }
}