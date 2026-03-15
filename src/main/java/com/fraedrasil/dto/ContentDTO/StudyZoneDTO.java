package com.fraedrasil.dto.ContentDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class StudyZoneDTO {
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
    public StudyZoneDTO(String name) {
        this.name = name;
    }

    public StudyZoneDTO(String name, AspectDTO aspect, List<StudyTaskDTO> studyTasks) {
        this.name = name;
        this.aspect = aspect;
        this.studyTasks.addAll(studyTasks);
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
