package resource;

import jpautil.JpaUtil;
import model.Measurement;
import model.Patient;
import org.restlet.resource.*;
import repository.MeasurementRepository;
import repository.PatientRepository;
import representation.MeasurementRepresentation;

import javax.persistence.EntityManager;

public class MeasurementResource extends ServerResource {

    private int measurementId;

    @Override
    protected void doInit() {
        measurementId = Integer.parseInt(getAttribute("measurementId"));
    }

    @Get("json")
    public MeasurementRepresentation getMeasurement() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(measurementId);
        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return measurementRepresentation2;
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

    @Put("json")
    public MeasurementRepresentation putMeasurement(MeasurementRepresentation measurementRepresentation) {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository measurementRepository = new MeasurementRepository(em);
        Measurement measurement = measurementRepository.read(measurementId);
        measurement.setDate(measurementRepresentation.getDate());
        measurement.setCarbIn(measurementRepresentation.getCarbIn());
        measurement.setGlucoseLevel(measurementRepresentation.getGlucoseLevel());
        measurementRepository.save(measurement);
        MeasurementRepresentation measurementRepresentation2 = new MeasurementRepresentation(measurement);
        em.close();
        return measurementRepresentation2;
    }

    @Delete("txt")
    public boolean deleteMeasurement() {
        EntityManager em = JpaUtil.getEntityManager();
        MeasurementRepository patientRepository = new MeasurementRepository(em);
        return patientRepository.delete(measurementId);
    }

}
