package com.fraedrasil.mapper.TaskUserMapper;

import com.fraedrasil.dto.TaskUserDTO.TaskResponseDTO;
import com.fraedrasil.entity.StudyTask;

public class TaskResponseMapper {


    public static TaskResponseDTO toDTO(StudyTask task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getQuestion(),
                task.getDifficulty());

    }


//    public static StudyTask toEntity(TaskResponseDTO taskResponseDTO) {
//        StudyTask task = new StudyTask();
//        task.setId(taskResponseDTO.getId());
//        task.setDifficulty(taskResponseDTO.getDifficulty());
//        task.setTitle(taskResponseDTO.getTitle());
//        task.setQuestion(taskResponseDTO.getQuestion());
//
//        return task ;
//    }


}
