package com.fraedrasil.dto;

public class TaskResponseDTO {

    private Long id ;
    private  String title ;
    private String question ;
    private int difficulty ;

    public TaskResponseDTO(Long id , String title , String question , int difficulty){
        this.id=id;
        this.title=title;
        this.question=question;
        this.difficulty=difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
