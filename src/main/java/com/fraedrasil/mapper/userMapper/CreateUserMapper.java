package com.fraedrasil.mapper.userMapper;

import com.fraedrasil.dto.UserDto.CreateUserDTO;
import com.fraedrasil.entity.User;

public class CreateUserMapper {
    public static User toEntity(CreateUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;

    }

    public static CreateUserDTO createUserDTO(User user) {

        return new CreateUserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
    }


}
