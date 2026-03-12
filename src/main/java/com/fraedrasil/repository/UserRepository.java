package com.fraedrasil.repository;

import com.fraedrasil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Long id(Long id);
}