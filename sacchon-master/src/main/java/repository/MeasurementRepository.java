package repository;

import model.Measurement;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class MeasurementRepository extends Repository<Measurement, Integer> {

    private EntityManager entityManager;

    public MeasurementRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return Measurement.class;
    }

    @Override
    public String getClassName() {
        return Measurement.class.getName();
    }

    /**
     * Retrieves a list of measurements for the given time range.
     *
     * @param patientId The identification of the patient.
     * @return A list of measurements.
     */
    public List<Measurement> findPatientMeasurements(int patientId) {
        return entityManager
                .createQuery("SELECT m from Measurement m " +
                        "WHERE m.patientId.patientId = :patientId", Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    /**
     * Retrieves a list of measurements for the given time range.
     *
     * @param fromDate The starting date.
     * @param toDate   The ending date.
     * @return A list of measurements.
     */
    public List<Measurement> findMeasurementsByTimeRange(Date fromDate, Date toDate) {
        return entityManager.createQuery("SELECT m from " +
                "Measurement m where m.date >= :fromDate and m.date <= :toDate", Measurement.class)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();

    }
}