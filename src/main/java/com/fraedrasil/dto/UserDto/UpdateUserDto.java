package com.fraedrasil.dto.UserDto;

public class UpdateUserDto {

    private String username;

    public UpdateUserDto(String name) {
        username = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
