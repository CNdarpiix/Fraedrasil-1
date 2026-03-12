package com.fraedrasil.controller;

import com.fraedrasil.dto.UserDto.CreateUserDTO;
import com.fraedrasil.dto.UserDto.GetUserDTO;
import com.fraedrasil.dto.UserDto.UpdateUserDto;
import com.fraedrasil.dto.UserProgressionDTO;
import com.fraedrasil.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CreateUserDTO createUser(@Valid @RequestBody CreateUserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping
    public List<GetUserDTO> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public GetUserDTO gteUserById(@PathVariable Long id) {
        return userService.findByIdDto(id);
    }

    @GetMapping("/{id}/progression")
    public UserProgressionDTO getUserProgression(@PathVariable Long id) {
        return userService.userProgression(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UpdateUserDto updateUser(@Valid @PathVariable Long id, @RequestBody UpdateUserDto user) {
        return userService.updateUserByIdDto(id, user);
    }
}
