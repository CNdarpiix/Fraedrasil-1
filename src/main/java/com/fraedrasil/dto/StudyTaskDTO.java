package com.fraedrasil.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class StudyTaskDTO {
    private Long id ;

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

    @Positive
    private int estimatedMinutes ;

    @Positive
    private int difficulty ;

    public StudyTaskDTO(String title , String description , String question , List<String> option , List<Integer> answer , int estimatedMinute , int difficulty){
        this.title = title ;
        this.description = description ;
        this.question = question ;
        this.option = option ;
        this.answer = answer ;
        this.estimatedMinutes = estimatedMinute ;
        this.difficulty = difficulty ;
    }


    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getEstimatedMinute() {
        return estimatedMinutes;
    }

    public void setEstimatedMinute(int estimatedMinute) {
        this.estimatedMinutes = estimatedMinute;
    }
}
