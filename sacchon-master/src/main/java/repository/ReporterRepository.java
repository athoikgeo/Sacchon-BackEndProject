package repository;


import model.Reporter;

import javax.persistence.EntityManager;

public class ReporterRepository extends Repository<Reporter, Integer> {
    private EntityManager entityManager;

    public ReporterRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Reporter> getEntityClass() {
        return Reporter.class;
    }

    @Override
    public String getClassName() {
        return Reporter.class.getName();
    }

    /**
     * Find the reporter by the given username.
     *
     * @param username the username of the reporter.
     * @return the reporter with the given username.
     */
    public Reporter getByUsername(String username) {
        return entityManager.createQuery("SELECT rep FROM Reporter rep " +
                "WHERE rep.username = :username", Reporter.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}