package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Domain {
    /// Atribut
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name ;

    @OneToMany(mappedBy = "domain" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Aspect> aspects ;

    /// Constructeur
    public Domain(){
    }

    /// Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Aspect> getAspects() {
        return aspects;
    }

    /// Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAspects(List<Aspect> aspects) {
        this.aspects = aspects;
    }
}
