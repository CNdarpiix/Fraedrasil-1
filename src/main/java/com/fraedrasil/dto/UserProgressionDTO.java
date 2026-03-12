package com.fraedrasil.dto;

public class UserProgressionDTO {
    private Long id ;
    private String username ;
    private int cosmeticLvl ;
    private int cosmeticXp ;

    private int taskStarted ;

    public UserProgressionDTO(Long id , String username,int cosmeticLvl , int cosmeticXp , int taskStarted){
        this.id=id ;
        this.username=username;
        this.cosmeticLvl=cosmeticLvl;
        this.cosmeticXp = cosmeticXp;
        this.taskStarted = taskStarted ;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getCosmeticLvl() {
        return cosmeticLvl;
    }

    public int getTaskStarted() {
        return taskStarted;
    }
}
