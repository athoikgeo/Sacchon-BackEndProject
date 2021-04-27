package resource;

import jpautil.JpaUtil;
import model.Measurement;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import repository.MeasurementRepository;
import representation.MeasurementRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientMeasurementsResource extends ServerResource {

    private int patientId;

    @Override
    protected void doInit() {
        patientId = Integer.parseInt(getAttribute("patientId"));
    }

    @Get("json")
    public List<MeasurementRepresentation> getPatientsMeasurements() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        List<Measurement> measurements = measurementRepository.findPatientMeasurements(patientId);
        em.close();
        return measurements.stream()
                .map(MeasurementRepresentation::new)
                .collect(toList());
    }
}

