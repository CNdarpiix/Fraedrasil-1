package com.fraedrasil.mapper.userMapper;

import com.fraedrasil.dto.UserDto.CreateUserDTO;
import com.fraedrasil.dto.UserDto.GetUserDTO;
import com.fraedrasil.entity.User;

public class GetUserMapper {
    public static User toEntity(GetUserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());

        return user;

    }

    public static GetUserDTO getUserDTO(User user) {
        return new GetUserDTO(user.getId(),user.getUsername(),user.getEmail());
    }


}
