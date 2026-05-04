package com.fraedrasil.dto.TaskUserDTO;

import java.util.List;

public class PlayTaskRequestDTO {
    /// Attributd

    private List<Integer> answers;

    /// getters

    public List<Integer> getAnswers() {
        return answers;
    }

    /// Setters

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
