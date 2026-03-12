package com.fraedrasil.service;

import com.fraedrasil.dto.StudyTaskDTO;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.StudyZone;
import com.fraedrasil.entity.User;
import com.fraedrasil.entity.UserZoneProgress;
import com.fraedrasil.exception.UserNotFoundException;
import com.fraedrasil.exception.UserZoneProgressException;
import com.fraedrasil.exception.ZoneNotFoundException;
import com.fraedrasil.repository.StudyTaskRepository;
import com.fraedrasil.repository.StudyZoneRepository;
import com.fraedrasil.repository.UserRepository;

import com.fraedrasil.repository.UserZoneProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyTaskService {
    private final StudyTaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserZoneProgressRepository userZoneProgressRepository;
    private final StudyZoneRepository studyZoneRepository;

    public StudyTaskService(StudyTaskRepository taskRepository, UserRepository userRepository, UserZoneProgressRepository userZoneProgressRepository, StudyZoneRepository studyZoneRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userZoneProgressRepository = userZoneProgressRepository;
        this.studyZoneRepository = studyZoneRepository;
    }

    @Transactional
    public StudyTask createTaskDTO(StudyTaskDTO taskDTO , Long zoneId) {

        StudyTask task = new StudyTask();
        task.setTitle(taskDTO.getTitle());
        task.setQuestion(taskDTO.getQuestion());
        task.setDescription(taskDTO.getDescription());
        task.setOption(taskDTO.getOption());
        task.setAnswer(taskDTO.getAnswer());
        task.setDifficulty(taskDTO.getDifficulty());
        task.setEstimatedMinutes(taskDTO.getEstimatedMinute());

        StudyZone zone = studyZoneRepository.findById(zoneId).orElseThrow(()-> new ZoneNotFoundException("Zone not found"));

        task.setStudyZone(zone);
        return taskRepository.save(task);
    }


    @Transactional
    public List<StudyTask> getAvailableTasks(Long userId, Long zoneId) {
        UserZoneProgress progress = userZoneProgressRepository
                .findByUserIdAndZoneId(userId, zoneId)
                .orElseThrow(() -> new UserZoneProgressException("Progress not Found"));
        return taskRepository
                .findByStudyZoneIdAndRequiredResponsibilityLevelLessThanEqual(zoneId, progress.getResponsibilityLevel());
    }


}
