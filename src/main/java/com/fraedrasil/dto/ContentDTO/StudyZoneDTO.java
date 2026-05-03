package com.fraedrasil.dto.ContentDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class StudyZoneDTO {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aspect_id", nullable = false)
    @JsonIgnore
    private AspectDTO aspect;

    @OneToMany(mappedBy = "studyZone", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudyTaskDTO> studyTasks;

    ///  Constructors
    public StudyZoneDTO(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public StudyZoneDTO(Long id, String name, AspectDTO aspect) {
        this.id = id;
        this.name = name;
        this.aspect = aspect;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /// Getters & Setters


    public List<StudyTaskDTO> getStudyTasks() {
        return studyTasks;
    }

    public void setStudyTasks(List<StudyTaskDTO> studyTasks) {
        this.studyTasks.addAll(studyTasks);
    }

    public AspectDTO getAspect() {

        return aspect;
    }

    public void setAspect(AspectDTO aspect) {
        this.aspect = aspect;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
