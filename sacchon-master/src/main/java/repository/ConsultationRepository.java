package repository;

import model.Consultation;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ConsultationRepository extends Repository<Consultation, Integer> {

    private final EntityManager entityManager;

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Consultation> getEntityClass() {
        return Consultation.class;
    }

    @Override
    public String getClassName() {
        return Consultation.class.getName();
    }

    /**
     * Retrieves a list of consultations for the given time range.
     *
     * @param fromDate The starting date.
     * @param toDate   The ending date.
     * @return A list of consultations.
     */
    public List<Consultation> findConsultationsByTimeRange(Date fromDate, Date toDate) {

        return entityManager.createQuery("from Consultation c where c.date >= :fromDate and c.date <= :toDate", Consultation.class)
                .setParameter("fromDate", fromDate)
                .setParameter("toDate", toDate)
                .getResultList();
    }

    /**
     * Retrieves a list of consultations for the specified patient.
     *
     * @param patientId The identification of the patient.
     * @return A list of consultations.
     */
    public List<Consultation> findPatientsConsultations(int patientId) {
        return entityManager
                .createQuery("SELECT c from Consultation c WHERE c.patientId.patientId = :patientId", Consultation.class)
                .setParameter("patientId", patientId)
                .getResultList();
    }

    /**
     * Retrieves a list of consultations for the given time range.
     *
     * @param startDate The starting date.
     * @param endDate   The ending date.
     * @return A list of consultations.
     */
    public List<Consultation> findDoctorsActivity(Date startDate, Date endDate) {

        List<Consultation> ds = entityManager.createQuery("SELECT DISTINCT d from Doctor d INNER JOIN Consultation c ON c.doctorId.doctorId = d.doctorId WHERE c.date >= :startDate AND c.date <= :endDate", Consultation.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return ds;
    }

}