package repository;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class Repository<T, K> {

    private final EntityManager entityManager;

    protected Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Saves an Entity.
     *
     * @param t The Entity to be saved.
     * @return the saved entity.
     */
    public T save(T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves an entity matching the provided id.
     *
     * @return The a
     */
    public abstract Class<T> getEntityClass();

    /**
     * Retrieves all the entities.
     *
     * @return a List of the asked entities.
     */
    public List<T> findAll() {
        return entityManager.createQuery("from " + getClassName()).getResultList();
    }

    /**
     * Updates the old entity with the given one.
     *
     * @param t The new entity.
     * @return The updated entity.
     */
    public T update(T t) {
        return save(t);
    }

    /**
     * Deletes an entity.
     *
     * @param id The identification of the entity
     * @return A {@link Boolean} if it was deleted ot not.
     */
    public boolean delete(K id) {
        T t = read(id);
        if (t == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T read(K id) {
        return entityManager.find(getEntityClass(), id);
    }

    public abstract String getClassName();
}
