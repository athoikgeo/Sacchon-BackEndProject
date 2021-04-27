package resource;

import jpautil.JpaUtil;
import model.Measurement;
import model.Patient;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import repository.MeasurementRepository;
import repository.PatientRepository;
import representation.MeasurementRepresentation;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MeasurementListResource extends ServerResource {

    @Get("json")
    public List<MeasurementRepresentation> getMeasurements() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        List<Measurement> measurements = measurementRepository.findAll();
        em.close();
        List<MeasurementRepresentation> measurementRepresentations =
                measurements.stream()
                        .map(MeasurementRepresentation::new)
                        .collect(toList());

        return measurementRepresentations;
    }

    @Post("json")
    public MeasurementRepresentation add(MeasurementRepresentation measurementRepresentation) {
        if (measurementRepresentation == null) return null;
        if (measurementRepresentation.getDate() == null) return null;
        if (measurementRepresentation.getCarbIn() == 0f) return null;
        if (measurementRepresentation.getGlucoseLevel() == 0f) return null;
        if (measurementRepresentation.getPatientId() == 0) return null;

        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(measurementRepresentation.getPatientId());

        Measurement measurement = measurementRepresentation.createMeasurement();
        measurement.setDate(measurementRepresentation.getDate());
        measurement.setPatientId(patient);
        measurement.setCarbIn(measurementRepresentation.getCarbIn());
        measurement.setGlucoseLevel(measurementRepresentation.getGlucoseLevel());
        measurementRepository.save(measurement);

        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return measurementRepresentation2;
    }
}
