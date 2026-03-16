package com.fraedrasil.service;

import com.fraedrasil.dto.ContentDTO.AspectDTO;
import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.dto.ContentDTO.StudyZoneDTO;
import com.fraedrasil.entity.Aspect;
import com.fraedrasil.entity.Domain;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.StudyZone;
import com.fraedrasil.exception.ContentException.AspectNotFoundException;
import com.fraedrasil.exception.ContentException.DomainNotFoundException;
import com.fraedrasil.exception.ZoneNotFoundException;
import com.fraedrasil.repository.AspectRepository;
import com.fraedrasil.repository.DomainRepository;
import com.fraedrasil.repository.StudyZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final DomainRepository domainRepository;
    private final AspectRepository aspectRepository;
    private final StudyZoneRepository studyZoneRepository;

    public ContentService(DomainRepository domainRepository, AspectRepository aspectRepository, StudyZoneRepository studyZoneRepository) {
        this.domainRepository = domainRepository;
        this.aspectRepository = aspectRepository;
        this.studyZoneRepository = studyZoneRepository;
    }

    ///  Domains
    public List<DomainDTO> getAllDomain() {
        return domainRepository.findAll()
                .stream()
                .map(domain -> new DomainDTO(
                        domain.getName())
                ).toList();
    }

    public DomainDTO getDomainById(Long domainId) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new DomainNotFoundException("Domain not found by id :" + domainId));

        return new DomainDTO(domain.getName());
    }

    /// Aspect

    public List<AspectDTO> getAllAspectFromDomainId(Long domainId) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new DomainNotFoundException("Domain not found by id :" + domainId));

        return domain.
                getAspects()
                .stream()
                .map(aspect -> new AspectDTO(aspect.getName()))
                .toList();
    }

    public AspectDTO getAspectFromId(Long aspectId) {
        Aspect aspect = this.aspectRepository.findById(aspectId)
                .orElseThrow(() -> new AspectNotFoundException("Aspect not found by id :" + aspectId));

        Domain domain = aspect.getDomain();

        DomainDTO domainDTO = new DomainDTO(domain.getName());

        return new AspectDTO(aspect.getName(), domainDTO);
    }

    /// StudyZone

    public List<StudyZoneDTO> getAllZoneFromAspectId(Long aspectId) {
        Aspect aspect = this.aspectRepository.findById(aspectId)
                .orElseThrow(() -> new AspectNotFoundException("Aspect not found by id :" + aspectId));

        return aspect
                .getZones()
                .stream()
                .map(StudyZone -> new StudyZoneDTO(StudyZone.getName())
                ).toList();
    }

    public StudyZoneDTO getZoneFromId(Long zoneId) {
        StudyZone zone = studyZoneRepository.findById(zoneId)
                .orElseThrow(() -> new ZoneNotFoundException("Zone not found by id :" + zoneId));

        AspectDTO aspectDTO = new AspectDTO(zone.getAspect().getName());

        return new StudyZoneDTO(zone.getName(), aspectDTO);
    }
}
