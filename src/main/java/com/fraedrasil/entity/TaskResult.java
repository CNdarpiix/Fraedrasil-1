package com.fraedrasil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task_result")
public class TaskResult {
    /// Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private StudyTask task;

    @Column(nullable = false)
    private List<Integer> answer;

    private int clarityScore;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /// Constructeurs
    public TaskResult(){}

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    ///Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public StudyTask getTask() {
        return task;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public int getClarityScore() {
        return clarityScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    /// Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTask(StudyTask task) {
        this.task = task;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void setClarityScore(int clarityScore) {
        this.clarityScore = clarityScore;
    }

}
