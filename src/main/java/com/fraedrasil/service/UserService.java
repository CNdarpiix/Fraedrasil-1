package com.fraedrasil.service;

import com.fraedrasil.dto.UserDto.CreateUserDTO;
import com.fraedrasil.dto.UserDto.GetUserDTO;
import com.fraedrasil.dto.UserDto.UpdateUserDto;
import com.fraedrasil.dto.TaskUserDTO.UserProgressionDTO;
import com.fraedrasil.entity.User;
import com.fraedrasil.exception.UserNotFoundException;
import com.fraedrasil.mapper.TaskUserMapper.UserProgressionMapper;
import com.fraedrasil.mapper.userMapper.CreateUserMapper;
import com.fraedrasil.mapper.userMapper.GetUserMapper;
import com.fraedrasil.mapper.userMapper.UpdateUserMapper;
import com.fraedrasil.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserDTO save(CreateUserDTO userDTO) {
       User user = CreateUserMapper.toEntity(userDTO);

        user = userRepository.save(user);
        return CreateUserMapper.createUserDTO(user);
    }

    public List<GetUserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(GetUserMapper::getUserDTO)
                .toList();
    }


    public UserProgressionDTO userProgression(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserProgressionMapper.toDTO(user);
    }

    public GetUserDTO findByIdDto(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return GetUserMapper.getUserDTO(user);
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);

        } else {
            throw new UserNotFoundException("User not Found");
        }
    }
    @Transactional
    public UpdateUserDto updateUserByIdDto(Long id, UpdateUserDto updateUser) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :" + id));

        existingUser.setUsername(updateUser.getUsername());


        return UpdateUserMapper.updateUserDto(existingUser);
    }


}
