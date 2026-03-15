package com.fraedrasil.dto.ContentDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fraedrasil.entity.Aspect;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class DomainDTO {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name ;

    @OneToMany(mappedBy = "domain" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AspectDTO> aspects ;

    public DomainDTO(String name) {
        this.name = name;
    }

    public DomainDTO(String name , List<AspectDTO> aspects) {
        this.name = name;
        this.aspects.addAll(aspects);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AspectDTO> getAspects() {
        return aspects;
    }

    public void setAspects(List<AspectDTO> aspects) {
        this.aspects.addAll(aspects);
    }
}
