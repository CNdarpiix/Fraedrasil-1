package com.fraedrasil.controller;


import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/content")
public class ContentController {

    final ContentService contentService ;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("api/content/domain/")
    public List<DomainDTO> getAllDomainDto(){
        return contentService.getAllDomain() ;
    }

    @GetMapping("api/content/domain/{domainId}")
    public DomainDTO getDomainById(@PathVariable Long domainId){
        return contentService.getDomainById(domainId);
    }

}
