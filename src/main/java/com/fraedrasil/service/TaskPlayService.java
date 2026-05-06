package com.fraedrasil.service;

import com.fraedrasil.dto.TaskUserDTO.TaskResponseDTO;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.TaskResult;
import com.fraedrasil.entity.User;
import com.fraedrasil.exception.TaskNotFoundException;
import com.fraedrasil.exception.UserNotFoundException;
import com.fraedrasil.mapper.TaskUserMapper.TaskResponseMapper;
import com.fraedrasil.repository.StudyTaskRepository;
import com.fraedrasil.repository.TaskResultRepository;
import com.fraedrasil.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TaskPlayService {

    private final UserZoneProgressService userZoneProgressService;
    private final TaskResultRepository taskResultRepository;
    private final StudyTaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskPlayService(
            TaskResultRepository taskResultRepository,
            StudyTaskRepository taskRepository,
            UserRepository userRepository,
            UserZoneProgressService userZoneProgressService
    ) {
        this.taskResultRepository = taskResultRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userZoneProgressService = userZoneProgressService;
    }

    public TaskResult playTask(
            Long userId,
            Long taskId,
            List<Integer> answers
    ) {

        User user = findUser(userId);

        StudyTask task = findTask(taskId);

        int clarityScore = calculateClarity(task, answers);

        TaskResult result = new TaskResult();
        result.setTask(task);
        result.setUser(user);
        result.setClarityScore(clarityScore);
        result.setAnswer(answers);

        TaskResult savedResult =
                taskResultRepository.save(result);

        Long zoneId = task.getStudyZone().getId();

        userZoneProgressService.processClarity(
                userId,
                zoneId,
                clarityScore
        );

        return savedResult;
    }

    private int calculateClarity(
            StudyTask task,
            List<Integer> answers
    ) {

        Set<Integer> correctAnswers =
                new HashSet<>(task.getAnswer());

        Set<Integer> uniqueAnswers =
                new HashSet<>(answers);

        int score = 0;

        int reward =
                10 / correctAnswers.size();

        for (Integer ans : uniqueAnswers) {

            if (correctAnswers.contains(ans)) {
                score += reward;
            } else {
                score -= 2;
            }
        }

        return Math.max(score, 0);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"));
    }

    private StudyTask findTask(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found"));
    }

    @Transactional
    public TaskResponseDTO getTask(Long taskId) {
        StudyTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id :" + taskId));

        return TaskResponseMapper.toDTO(task);
    }
}
