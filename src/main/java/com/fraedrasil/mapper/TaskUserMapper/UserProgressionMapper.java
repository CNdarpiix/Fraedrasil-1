package com.fraedrasil.mapper.TaskUserMapper;

import com.fraedrasil.dto.TaskUserDTO.TaskResponseDTO;
import com.fraedrasil.dto.TaskUserDTO.UserProgressionDTO;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.User;
import com.fraedrasil.entity.UserZoneProgress;

public class UserProgressionMapper {


    public static UserProgressionDTO toDTO(User user) {
        return new UserProgressionDTO(
                user.getId() ,
                user.getUsername() ,
                user.getCosmeticLvl() ,
                user.getCosmeticXp(),
                user.getStreak());

    }




}
