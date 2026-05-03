package com.fraedrasil.dto.ContentDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class AspectDTO {
    /// Attributs
    @Id
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", nullable = false)
    @JsonIgnore
    private DomainDTO domain;

    @OneToMany(mappedBy = "aspect", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudyZoneDTO> zones;


    /// Constructors

    public AspectDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AspectDTO(Long id, String name, DomainDTO domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
    }


    ///  Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StudyZoneDTO> getZones() {
        return zones;
    }

    public void setZones(List<StudyZoneDTO> zones) {
        this.zones = zones;
    }

    public DomainDTO getDomain() {
        return domain;
    }

    public void setDomain(DomainDTO domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
