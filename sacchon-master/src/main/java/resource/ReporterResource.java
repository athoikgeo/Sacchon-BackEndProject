package resource;

import jpautil.JpaUtil;
import model.Reporter;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import repository.ReporterRepository;
import representation.ReporterRepresentation;

import javax.persistence.EntityManager;

public class ReporterResource extends ServerResource {

    private int reporterId;

    @Override
    protected void doInit() {
        reporterId = Integer.parseInt(getAttribute("reporterId"));
    }

    @Get("json")
    public ReporterRepresentation getReporter() {
        EntityManager em = JpaUtil.getEntityManager();
        ReporterRepository reporterRepository = new ReporterRepository(em);
        Reporter reporter = reporterRepository.read(reporterId);
        ReporterRepresentation reporterRepresentation2 = new ReporterRepresentation(reporter);
        em.close();
        return reporterRepresentation2;
    }

    @Put("json")
    public ReporterRepresentation putReporter(ReporterRepresentation reporterRepresentation) {
        EntityManager em = JpaUtil.getEntityManager();
        ReporterRepository reporterRepository = new ReporterRepository(em);
        Reporter reporter = reporterRepository.read(reporterId);
        reporter.setPassword(reporterRepresentation.getPassword());
        reporter.setUsername(reporterRepresentation.getUsername());
        reporter.setLastName(reporterRepresentation.getLastName());
        reporter.setFirstName(reporterRepresentation.getFirstName());
        reporterRepository.save(reporter);
        ReporterRepresentation reporterRepresentation2 = new ReporterRepresentation(reporter);
        em.close();
        return reporterRepresentation2;
    }

    @Delete("txt")
    public boolean deleteReporter() {
        EntityManager em = JpaUtil.getEntityManager();
        ReporterRepository reporterRepository = new ReporterRepository(em);
        return reporterRepository.delete(reporterId);
    }

}
