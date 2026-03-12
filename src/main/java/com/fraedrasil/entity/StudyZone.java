package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class StudyZone {
    /// Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank
    private String name ;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "aspect_id",nullable = false)
    @JsonIgnore
    private Aspect aspect ;

    @OneToMany(mappedBy = "studyZone", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudyTask> studyTasks ;

    @OneToMany(mappedBy = "zone" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserZoneProgress> userZoneProgresses ;
    /// Constructeur
    public StudyZone(){
    }
    /// getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Aspect getAspect() {
        return aspect;
    }
    public List<StudyTask> getStudyTasks() {
        return studyTasks;
    }

    public List<UserZoneProgress> getUserZoneProgresses() {
        return userZoneProgresses;
    }

    /// Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

    public void setStudyTasks(List<StudyTask> studyTasks) {
        this.studyTasks = studyTasks;
    }

    public void setUserZoneProgresses(List<UserZoneProgress> userZoneProgresses) {
        this.userZoneProgresses = userZoneProgresses;
    }
}
