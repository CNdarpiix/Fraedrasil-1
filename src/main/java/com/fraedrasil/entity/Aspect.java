package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


@Entity
public class Aspect {
    /// Atributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", nullable = false)
    @JsonIgnore
    private Domain domain;

    @OneToMany(mappedBy = "aspect", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudyZone> zones;

    /// Constructeurs
    public Aspect(){
    }

    /// Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Domain getDomain() {
        return domain;
    }

    public List<StudyZone> getZones() {
        return zones;
    }

    /// Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public void setZones(List<StudyZone> zones) {
        this.zones = zones;
    }
}
