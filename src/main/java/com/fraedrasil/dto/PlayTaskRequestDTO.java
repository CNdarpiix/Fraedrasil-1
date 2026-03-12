package com.fraedrasil.dto;

import java.util.List;

public class PlayTaskRequestDTO {
    /// Attributd
    private Long taskId;

    private List<Integer> answers;

    /// getters

    public Long getUserId() {
        return taskId;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    /// Setters

    public void setUserId(Long taskId) {
        this.taskId = taskId;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
