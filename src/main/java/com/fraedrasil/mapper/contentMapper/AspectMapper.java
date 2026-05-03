package com.fraedrasil.mapper.contentMapper;

import com.fraedrasil.dto.ContentDTO.AspectDTO;
import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.dto.ContentDTO.StudyTaskDTO;
import com.fraedrasil.dto.ContentDTO.StudyZoneDTO;
import com.fraedrasil.entity.Aspect;
import com.fraedrasil.entity.StudyZone;

import java.util.List;

public class AspectMapper {
    public static AspectDTO toDTO(Aspect aspect) {
        AspectDTO aspectDTO = new AspectDTO(aspect.getId(), aspect.getName());

        DomainDTO domainDTO = DomainMapper.toDTO(aspect.getDomain());
        List<StudyZoneDTO> studyZoneDTOS = aspect.getZones().stream().map(StudyZoneMapper::toDTO).toList();

        aspectDTO.setDomain(domainDTO);
        aspectDTO.setZones(studyZoneDTOS);

        return aspectDTO;
    }

    public static Aspect toEntity(AspectDTO aspectDTO) {
        Aspect aspect = new Aspect();
        aspect.setId(aspectDTO.getId());
        aspect.setDomain(DomainMapper.toEntity(aspectDTO.getDomain()));
        aspect.setZones(aspectDTO.getZones().stream().map(StudyZoneMapper::toEntity).toList());
        aspect.setName(aspect.getName());
        return aspect;
    }
}
