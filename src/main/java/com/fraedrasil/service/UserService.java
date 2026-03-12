package com.fraedrasil.service;

import com.fraedrasil.dto.UserDto.CreateUserDTO;
import com.fraedrasil.dto.UserDto.GetUserDTO;
import com.fraedrasil.dto.UserDto.UpdateUserDto;
import com.fraedrasil.dto.UserProgressionDTO;
import com.fraedrasil.entity.User;
import com.fraedrasil.exception.UserNotFoundException;
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
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        userRepository.save(user);
        return new CreateUserDTO(user.getUsername() , user.getEmail() , user.getPassword());
    }

    public List<GetUserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new GetUserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()))
                .toList();
    }


    public UserProgressionDTO userProgression(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserProgressionDTO(user.getId(), user.getUsername(), user.getCosmeticLvl(), user.getCosmeticXp(), user.getTasks().size());
    }

    public GetUserDTO findByIdDto(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new GetUserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            ;
        } else {
            throw new UserNotFoundException("User not Found");
        }
    }
    @Transactional
    public UpdateUserDto updateUserByIdDto(Long id, UpdateUserDto updateUser) {

        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id :" + id));

        existingUser.setUsername(updateUser.getUsername());

        userRepository.save(existingUser);

        return new UpdateUserDto(existingUser.getUsername());
    }

    private User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id :" + userId));
    }
}
