package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "user_zone_progress",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "zone_id"})
)
public class UserZoneProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    @JsonIgnore
    private StudyZone zone;

    ///Progression
    private int responsibilityLevel;

    private int consecutiveHighClarity;

    private int totalAttempts;

    @ElementCollection
    @CollectionTable(
            name = "zone_clarity_history",
            joinColumns = @JoinColumn(name = "progress_id")
    )
    @Column(name = "clarity_score")
    private List<Integer> lastClarityScores = new ArrayList<>();

    private boolean stabilized;

    ///  Constructeurs

    public UserZoneProgress() {
    }

    /// Getters

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public StudyZone getZone() {
        return zone;
    }

    public int getResponsibilityLevel() {
        return responsibilityLevel;
    }

    public int getConsecutiveHighClarity() {
        return consecutiveHighClarity;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public List<Integer> getLastClarityScores() {
        return lastClarityScores;
    }

    public boolean isStabilized() {
        return stabilized;
    }

    ///Setters

    public void setUser(User user) {
        this.user = user;
    }

    public void setZone(StudyZone zone) {
        this.zone = zone;
    }

    public void setResponsibilityLevel(int responsibilityLevel) {
        this.responsibilityLevel = responsibilityLevel;
    }

    public void setConsecutiveHighClarity(int consecutiveHighClarity) {
        this.consecutiveHighClarity = consecutiveHighClarity;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public void setLastClarityScores(List<Integer> lastClarityScores) {
        this.lastClarityScores = lastClarityScores;
    }

    public void setStabilized(boolean stabilized) {
        this.stabilized = stabilized;
    }
}