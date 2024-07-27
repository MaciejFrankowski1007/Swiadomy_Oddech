package org.example.repository;

import org.example.domain.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {
    List<AirQuality> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
