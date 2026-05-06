package com.fraedrasil.service;

import com.fraedrasil.dto.TaskUserDTO.TaskResponseDTO;
import com.fraedrasil.entity.UserZoneProgress;
import com.fraedrasil.exception.UserZoneProgressException;
import com.fraedrasil.mapper.TaskUserMapper.TaskResponseMapper;
import com.fraedrasil.repository.StudyTaskRepository;


import com.fraedrasil.repository.UserZoneProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyTaskService {
    private final StudyTaskRepository taskRepository;
    private final UserZoneProgressRepository userZoneProgressRepository;

    public StudyTaskService(StudyTaskRepository taskRepository, UserZoneProgressRepository userZoneProgressRepository) {
        this.taskRepository = taskRepository;
        this.userZoneProgressRepository = userZoneProgressRepository;
    }


    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAvailableTasks(Long userId, Long zoneId) {
        UserZoneProgress progress = userZoneProgressRepository
                .findByUserIdAndZoneId(userId, zoneId)
                .orElseThrow(() -> new UserZoneProgressException("Progress not Found"));
        return taskRepository
                .findByStudyZoneIdAndRequiredResponsibilityLevelLessThanEqual(zoneId, progress.getResponsibilityLevel()).stream().map(TaskResponseMapper::toDTO).toList();
    }


}
