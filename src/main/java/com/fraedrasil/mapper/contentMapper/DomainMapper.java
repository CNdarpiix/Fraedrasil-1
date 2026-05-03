package com.fraedrasil.mapper.contentMapper;

import com.fraedrasil.dto.ContentDTO.AspectDTO;
import com.fraedrasil.dto.ContentDTO.DomainDTO;
import com.fraedrasil.entity.Aspect;
import com.fraedrasil.entity.Domain;

import java.util.ArrayList;
import java.util.List;

public class DomainMapper {

    public static DomainDTO toDTO (Domain domain){
       return new DomainDTO(domain.getId() , domain.getName() ,domain.getAspects().stream().map(AspectMapper::toDTO).toList());
    }

    public static Domain toEntity (DomainDTO domainDTO){
        Domain domain = new Domain();
        domain.setName(domainDTO.getName());
        domain.setAspects(domainDTO.getAspects().stream().map(AspectMapper::toEntity).toList());

        return  domain;
    }
}
