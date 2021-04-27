package repository;

import model.Measurement;
import model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class PatientRepository extends Repository<Patient, Integer> {

    private EntityManager entityManager;

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Patient> getEntityClass() {
        return Patient.class;
    }

    @Override
    public String getClassName() {
        return Patient.class.getName();
    }

    public Patient getByUsername(String username) {
        return entityManager.createQuery("SELECT pat FROM Patient pat WHERE pat.username = :username", Patient.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     * Retrieves the {@link Measurement}s of the specified {@link Patient}.
     *
     * @param patientId The id of the {@link Patient}.
     * @return A list of the patient's measurements.
     */
    public List<Measurement> findMeasurementByPatient(int patientId) {
        return entityManager.createQuery("from Measurement WHERE patientId = :patientId", Measurement.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    /**
     * Retrieves a list of active patients for the given time range.
     *
     * @param startDate The starting date.
     * @param endDate   The ending date.
     * @return A list of the active patients.
     */
    public List<Patient> findPatientsActivity(Date startDate, Date endDate) {

        List<Patient> ds = entityManager.createQuery("SELECT DISTINCT p from Patient p INNER JOIN Consultation c ON c.patientId.patientId = p.patientId WHERE c.date >= :startDate AND c.date <= :endDate", Patient.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return ds;
    }
}
