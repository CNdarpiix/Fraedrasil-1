package com.fraedrasil.controller;


import com.fraedrasil.dto.ContentDTO.AspectDTO;
import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.dto.ContentDTO.StudyZoneDTO;
import com.fraedrasil.service.ContentService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/content")
public class ContentController {

    final ContentService contentService ;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    ///  Domain

    @GetMapping("api/content/domain")
    public List<DomainDTO> getAllDomainDto(){
        return contentService.getAllDomain() ;
    }

    @GetMapping("api/content/domain/{domainId}")
    public DomainDTO getDomainById(@PathVariable Long domainId){
        return contentService.getDomainById(domainId);
    }

    /// Aspect

    @GetMapping("api/content/domain/{domainId}/aspect/")
    public List<AspectDTO> getAllAspectFromDomainId(@PathVariable Long domainId) {
        return contentService.getAllAspectFromDomainId(domainId);
    }

    @GetMapping("api/content/domain/aspect/{aspectId}")
    public AspectDTO getAspectFromId(@PathVariable Long aspectId){
        return contentService.getAspectFromId(aspectId);
    }

    /// Zone

    @GetMapping("api/content/domain/aspect/{aspectId}/zone/")
    public List<StudyZoneDTO> getAllZoneFromAspectId(@PathVariable Long aspectId){
        return contentService.getAllZoneFromAspectId(aspectId);
    }

    @GetMapping("api/content/domain/aspect/zone/{zoneId}")
    public StudyZoneDTO getZoneById(@PathVariable Long zoneId){
        return contentService.getZoneFromId(zoneId);
    }



}
