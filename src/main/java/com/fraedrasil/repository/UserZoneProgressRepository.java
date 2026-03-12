package com.fraedrasil.repository;

import com.fraedrasil.entity.UserZoneProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserZoneProgressRepository extends JpaRepository<UserZoneProgress,Long> {
    Optional<UserZoneProgress> findByUserIdAndZoneId(Long userId, Long zoneId);
}
