package com.fraedrasil.repository;

import com.fraedrasil.entity.StudyZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyZoneRepository extends JpaRepository<StudyZone , Long> {
    StudyZone findStudyZoneById(Long id);
}
