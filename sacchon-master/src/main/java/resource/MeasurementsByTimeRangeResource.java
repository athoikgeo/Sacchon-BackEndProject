package resource;

import jpautil.JpaUtil;
import model.Measurement;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import repository.MeasurementRepository;
import representation.MeasurementRepresentation;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MeasurementsByTimeRangeResource extends ServerResource {

    private Date fromDate;
    private Date toDate;

    @Get("json")
    public List<MeasurementRepresentation> getMeasurementsByTimeRange() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        List<Measurement> measurements = measurementRepository.findMeasurementsByTimeRange(fromDate, toDate);
        em.close();
        return measurements.stream()
                .map(MeasurementRepresentation::new)
                .collect(toList());
    }
}