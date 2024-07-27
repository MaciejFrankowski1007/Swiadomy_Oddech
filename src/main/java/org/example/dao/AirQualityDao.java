package org.example.dao;

import org.example.domain.AirQuality;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AirQualityDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(AirQuality airQuality) {
        entityManager.persist(airQuality);
    }

    public List<AirQuality> findByUserId(Long userId) {
        return entityManager.createQuery("SELECT a FROM AirQuality a WHERE a.user.id = :userId", AirQuality.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void deleteByUserId(Long userId) {
        entityManager.createQuery("DELETE FROM AirQuality a WHERE a.user.id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
