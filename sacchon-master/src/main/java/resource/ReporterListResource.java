package resource;

import jpautil.JpaUtil;
import model.Reporter;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.ReporterRepository;
import representation.ReporterRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReporterListResource extends ServerResource {

    @Get("json")
    public List<ReporterRepresentation> getReporters() {
        EntityManager em = JpaUtil.getEntityManager();
        ReporterRepository reporterRepository = new ReporterRepository(em);
        List<Reporter> reporters = reporterRepository.findAll();
        em.close();
        return reporters.stream()
                .map(ReporterRepresentation::new)
                .collect(toList());
    }

    @Post("json")
    public ReporterRepresentation add(ReporterRepresentation reporterRepresentationIn) {

        if (reporterRepresentationIn == null) return null;
        if (reporterRepresentationIn.getFirstName() == null) return null;
        if (reporterRepresentationIn.getLastName() == null) return null;
        if (reporterRepresentationIn.getPassword() == null) return null;
        if (reporterRepresentationIn.getUsername() == null) return null;
        if (reporterRepresentationIn.getRole() == null) return null;
        Reporter reporter = reporterRepresentationIn.createReporter();
        EntityManager em = JpaUtil.getEntityManager();
        ReporterRepository reporterRepository = new ReporterRepository(em);
        reporterRepository.save(reporter);
        ReporterRepresentation p = new ReporterRepresentation(reporter);
        return reporterRepresentationIn;
    }
}
