package com.fraedrasil.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    /// attribut
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    private int streak;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TaskResult> taskResults;

    @PositiveOrZero
    private int cosmeticXp ;

    @PositiveOrZero
    private int cosmeticLvl ;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserZoneProgress> userZoneProgresses ;

    //@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    // private List<StudySession> sessions ;

    /// constructeur
    public User() {
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
    }



    /// Get
    public String getUsername() {
        return username;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getStreak() {
        return streak;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<TaskResult> getTasks() {
        return taskResults;
    }

    public int getCosmeticXp() {
        return cosmeticXp;
    }

    public int getCosmeticLvl() {
        return cosmeticLvl;
    }

    public List<UserZoneProgress> getUserZoneProgresses() {
        return userZoneProgresses;
    }

    /// Set
    public void setUsername(String username) {
        this.username = username;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public void setTasks(List<TaskResult> tasks) {
        this.taskResults = tasks;
    }

    public void setComesticXp(int cosmeticXp) {
        this.cosmeticXp = cosmeticXp;
    }

    public void setComesticLvl(int cosmeticLvl) {
        this.cosmeticLvl = cosmeticLvl;
    }

    public void setUserZoneProgresses(List<UserZoneProgress> userZoneProgresses) {
        this.userZoneProgresses = userZoneProgresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
