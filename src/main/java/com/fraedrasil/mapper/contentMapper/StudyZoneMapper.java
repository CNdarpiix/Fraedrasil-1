package com.fraedrasil.mapper.contentMapper;

import com.fraedrasil.dto.ContentDTO.StudyZoneDTO;
import com.fraedrasil.entity.StudyZone;

public class StudyZoneMapper {

    public static StudyZoneDTO toDTO(StudyZone studyZone) {
        return new StudyZoneDTO(studyZone.getId(), studyZone.getName(), AspectMapper.toDTO(studyZone.getAspect()));
    }

    public static StudyZone toEntity(StudyZoneDTO studyZoneDTO) {
        StudyZone studyZone = new StudyZone();
        studyZone.setId(studyZoneDTO.getId());
        studyZone.setName(studyZoneDTO.getName());
        studyZone.setAspect(AspectMapper.toEntity(studyZoneDTO.getAspect()));
        studyZone.setStudyTasks(studyZoneDTO.getStudyTasks().stream().map(StudyTaskMapper::toEntity).toList());
        return studyZone;
    }
}
