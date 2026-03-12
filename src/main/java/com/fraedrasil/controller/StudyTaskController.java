package com.fraedrasil.controller;


import com.fraedrasil.dto.PlayTaskRequestDTO;
import com.fraedrasil.dto.StudyTaskDTO;
import com.fraedrasil.dto.TaskResponseDTO;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.TaskResult;
import com.fraedrasil.service.StudyTaskService;
import com.fraedrasil.service.TaskPlayService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class StudyTaskController {
    private final StudyTaskService studyTaskService;
    private final TaskPlayService taskPlayService;

    public StudyTaskController (StudyTaskService taskService, TaskPlayService taskPlayService) {
        this.studyTaskService = taskService;
        this.taskPlayService = taskPlayService;
    }

    @PostMapping("/{zoneId}")
    public StudyTask createTask(@PathVariable Long zoneId , @RequestBody StudyTaskDTO task) {
        return studyTaskService.createTaskDTO(task , zoneId);
    }


    @PostMapping("/{userId}/{taskId}/play")
    public TaskResult playTask(@PathVariable Long userId , @PathVariable Long taskId, @RequestBody PlayTaskRequestDTO request){
        return taskPlayService.playTask(userId, taskId , request.getAnswers());
    }

    @GetMapping
    public List<TaskResponseDTO> getTask(){
        return taskPlayService.getAllTasks();
    }

}//End class

