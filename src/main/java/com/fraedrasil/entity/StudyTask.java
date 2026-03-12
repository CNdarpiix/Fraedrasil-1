package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "study_tasks")
public class StudyTask {
    /// Atribut

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String question ;

    @ElementCollection
    private List<String> option ;

    @ElementCollection
    private List<Integer> answer ;

    @Min(1)
    @Max(5)
    private int difficulty;

    @Positive
    private int estimatedMinutes;

    private boolean completed = false;

    @Min(0)
    private int requiredResponsibilityLevel;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id" , nullable = false)
    @JsonIgnore
    private StudyZone studyZone ;

    public StudyTask() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }



    /// Getters

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public StudyZone getStudyZone() {
        return studyZone;
    }

    public int getRequiredResponsibilityLevel() {
        return requiredResponsibilityLevel;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOption() {
        return option;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    /// Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setEstimatedMinutes(int estimatedMinute) {
        this.estimatedMinutes = estimatedMinute;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public void setRequiredResponsibilityLevel(int requiredResponsibilityLevel) {
        this.requiredResponsibilityLevel = requiredResponsibilityLevel;
    }

    public void setStudyZone(StudyZone studyZone) {
        this.studyZone = studyZone;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}

