package com.fraedrasil.repository;

import com.fraedrasil.entity.*;
import com.fraedrasil.entity.TaskResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskResultRepository extends JpaRepository<TaskResult, Long> {

    List<TaskResult> findByUserId(Long userId);
    List<TaskResult> findByUserIdAndTaskId(Long userId, Long taskId);
}
