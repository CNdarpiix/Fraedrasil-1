package com.fraedrasil.controller;

import com.fraedrasil.dto.ContentDTO.*;
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

    @GetMapping("/allDomain")
    public List<DomainDTO> getAllDomainDto(){
        return contentService.getAllDomain() ;
    }

    @GetMapping("/domain/{domainId}")
    public DomainDTO getDomainById(@PathVariable Long domainId){
        return contentService.getDomainById(domainId);
    }

    /// Aspect

    @GetMapping("/domain/{domainId}/allAspect/")
    public List<AspectDTO> getAllAspectFromDomainId(@PathVariable Long domainId) {
        return contentService.getAllAspectFromDomainId(domainId);
    }

    @GetMapping("/domain/aspect/{aspectId}")
    public AspectDTO getAspectFromId(@PathVariable Long aspectId){
        return contentService.getAspectFromId(aspectId);
    }

    /// Zone

    @GetMapping("/domain/aspect/{aspectId}/allZone/")
    public List<StudyZoneDTO> getAllZoneFromAspectId(@PathVariable Long aspectId){
        return contentService.getAllZoneFromAspectId(aspectId);
    }

    @GetMapping("/domain/aspect/zone/{zoneId}")
    public StudyZoneDTO getZoneById(@PathVariable Long zoneId){
        return contentService.getZoneFromId(zoneId);
    }

    /// Task

    @GetMapping("/domain/aspect/zone/{zoneId}/allTask")
    public List<StudyTaskDTO> getAllTaskFromZoneId(@PathVariable Long zoneId){
        return contentService.getAllTaskFromZoneId(zoneId);
    }

    @GetMapping("/domain/aspect/zone/task/{taskId}")
    public StudyTaskDTO getTaskById(@PathVariable Long taskId){
        return contentService.getTaskById(taskId);
    }

}
