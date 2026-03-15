package com.fraedrasil.service;

import com.fraedrasil.dto.ContentDTO.AspectDTO;
import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.entity.Domain;
import com.fraedrasil.exception.ContentException.DomainNotFoundException;
import com.fraedrasil.repository.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final DomainRepository domainRepository;

    public ContentService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

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

        List<AspectDTO> aspectDTOS = domain.
                getAspects()
                .stream()
                .map(aspect -> new AspectDTO(aspect.getName()))
                .toList() ;

        return new DomainDTO(domain.getName() , aspectDTOS);
    }
}
