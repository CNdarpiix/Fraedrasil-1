package com.fraedrasil.mapper.userMapper;

import com.fraedrasil.dto.UserDto.GetUserDTO;
import com.fraedrasil.dto.UserDto.UpdateUserDto;
import com.fraedrasil.entity.User;

public class UpdateUserMapper {
    public static User toEntity(UpdateUserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());

        return user;

    }

    public static UpdateUserDto updateUserDto(User user) {
        return new UpdateUserDto(user.getUsername());
    }


}
