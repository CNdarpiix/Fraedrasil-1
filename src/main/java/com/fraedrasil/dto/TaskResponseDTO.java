package com.fraedrasil.dto;

public class TaskResponseDTO {


    private  String title ;
    private String question ;
    private int difficulty ;

    public TaskResponseDTO( String title , String question , int difficulty){

        this.title=title;
        this.question=question;
        this.difficulty=difficulty;
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
