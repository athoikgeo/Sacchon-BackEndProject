package repository;

import model.Doctor;

import javax.persistence.EntityManager;

public class DoctorRepository extends Repository<Doctor, Integer> {

    private EntityManager entityManager;

    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    public String getClassName() {
        return Doctor.class.getName();
    }

    /**
     * Find the doctor by the given username.
     *
     * @param username the username of the doctor.
     * @return the doctor with the given username.
     */
    public Doctor getByUsername(String username) {
        return entityManager.createQuery("SELECT doc FROM Doctor doc WHERE doc.username = :username", Doctor.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}


