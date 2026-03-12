package com.fraedrasil.repository;

import com.fraedrasil.entity.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyTaskRepository extends JpaRepository<StudyTask , Long> {

    List<StudyTask> findByStudyZoneIdAndRequiredResponsibilityLevelLessThanEqual(Long zoneId, int level);
}
