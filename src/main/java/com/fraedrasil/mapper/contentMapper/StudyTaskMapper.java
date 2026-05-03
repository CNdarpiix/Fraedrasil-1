package com.fraedrasil.mapper.contentMapper;

import com.fraedrasil.dto.ContentDTO.StudyTaskDTO;
import com.fraedrasil.entity.StudyTask;
import org.hibernate.tool.schema.internal.StandardUserDefinedTypeExporter;

import java.util.ArrayList;

public class StudyTaskMapper {

    public static StudyTaskDTO toDTO(StudyTask studyTask) {
        return new StudyTaskDTO(studyTask.getId(),
                studyTask.getTitle(),
                studyTask.getDescription(),
                studyTask.getQuestion(),
                studyTask.getOption(),
                studyTask.getAnswer(),
                studyTask.getEstimatedMinutes(),
                studyTask.getDifficulty());
    }

    public static StudyTask toEntity(StudyTaskDTO studyTaskDTO) {
        StudyTask studyTask = new StudyTask();
        studyTask.setId(studyTaskDTO.getId());
        studyTask.setTitle(studyTaskDTO.getTitle());
        studyTask.setDescription(studyTaskDTO.getDescription());
        studyTask.setQuestion(studyTaskDTO.getQuestion());
        studyTask.setOption(new ArrayList<>(studyTaskDTO.getOption()));
        studyTask.setAnswer(new ArrayList<>(studyTaskDTO.getAnswer()));
        studyTask.setEstimatedMinutes(studyTaskDTO.getEstimatedMinutes());
        studyTask.setDifficulty(studyTaskDTO.getDifficulty());
        return studyTask;
    }

}
