package com.fraedrasil.service;

import com.fraedrasil.dto.TaskResponseDTO;
import com.fraedrasil.entity.*;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.TaskResult;
import com.fraedrasil.entity.User;
import com.fraedrasil.exception.TaskNotFoundException;
import com.fraedrasil.exception.UserNotFoundException;
import com.fraedrasil.repository.*;
import com.fraedrasil.repository.StudyTaskRepository;
import com.fraedrasil.repository.TaskResultRepository;
import com.fraedrasil.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskPlayService {
    private final UserZoneProgressService userZoneProgressService;
    private TaskResultRepository taskResultRepository;
    private StudyTaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskPlayService(
            TaskResultRepository taskResultRepository,
            StudyTaskRepository taskRepository,
            UserRepository userRepository,
            UserZoneProgressService userZoneProgressService) {
        this.taskResultRepository = taskResultRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userZoneProgressService = userZoneProgressService;
    }

    @Transactional
    public TaskResult playTask(Long userId, Long taskId, List<Integer> answer) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));//récupére l'user

        StudyTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));//récupére la task

        int clarityScore = calculateClarity(task, answer); // Calcule le score

        TaskResult newResult = new TaskResult();
        newResult.setTask(task);
        newResult.setUser(user);
        newResult.setClarityScore(clarityScore);
        newResult.setAnswer(answer);// crée le résultat de la task

        Long zoneId = task.getStudyZone().getId();// récupére la zone concérné

        userZoneProgressService.processClarity(userId, zoneId, clarityScore);// incrémente la progressions de la zone au joueurs

        TaskResult savedResult = taskResultRepository.save(newResult);
        return savedResult;
    }

    public int calculateClarity(StudyTask task, List<Integer> answers) {

        int score = 0;

        List<Integer> correctAnswers = task.getAnswer();

        for (Integer ans : answers) {

            if (correctAnswers.contains(ans)) {
                score += 10 / correctAnswers.size();
            } else {
                score -= 2;
            }

        }

        return Math.max(score, 0);
    }

    @Transactional
    public List<TaskResponseDTO> getAllTasks(){
        List<StudyTask> tasks = taskRepository.findAll();

        return tasks.stream().map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getQuestion(),
                task.getDifficulty()))
                .toList();
    }



}//End class
