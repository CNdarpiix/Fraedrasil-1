package com.fraedrasil.service;

import com.fraedrasil.entity.*;
import com.fraedrasil.entity.StudyZone;
import com.fraedrasil.entity.User;
import com.fraedrasil.entity.UserZoneProgress;
import com.fraedrasil.exception.UserNotFoundException;
import com.fraedrasil.exception.ZoneNotFoundException;
import com.fraedrasil.repository.*;

import com.fraedrasil.repository.StudyZoneRepository;
import com.fraedrasil.repository.UserRepository;
import com.fraedrasil.repository.UserZoneProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserZoneProgressService {
    private final UserRepository userRepository;
    private final StudyZoneRepository studyZoneRepository;
    private final UserZoneProgressRepository userZoneProgressRepository;

    public UserZoneProgressService(UserRepository userRepository, UserZoneProgressRepository userZoneProgressRepository, StudyZoneRepository studyZoneRepository) {
        this.userRepository = userRepository;
        this.studyZoneRepository = studyZoneRepository;
        this.userZoneProgressRepository = userZoneProgressRepository;
    }

    @Transactional
    public void processClarity(Long userId, Long zoneId, int clarityScore) {

        if (clarityScore < 0 || clarityScore > 10) {
            throw new IllegalArgumentException("Clarity score must be between 0 and 10");
        }

        // Récupérer ou créer la progression
        UserZoneProgress progress = userZoneProgressRepository
                .findByUserIdAndZoneId(userId, zoneId)
                .orElseGet(() -> {

                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new UserNotFoundException("User not found"));

                    StudyZone zone = studyZoneRepository.findById(zoneId)
                            .orElseThrow(() -> new ZoneNotFoundException("Zone not found"));

                    UserZoneProgress newProgress = new UserZoneProgress();
                    newProgress.setUser(user);
                    newProgress.setZone(zone);
                    newProgress.setResponsibilityLevel(0);
                    newProgress.setConsecutiveHighClarity(0);
                    newProgress.setTotalAttempts(0);

                    return userZoneProgressRepository.save(newProgress);
                });

        // Incrémenter tentative
        progress.setTotalAttempts(progress.getTotalAttempts() + 1);

        incrementStreak(progress, clarityScore);// incrémente la streak

        updateHistory(progress, clarityScore);// met a jours l'historique des réussite

        //promotion ou pas
        if (isPromote(progress)) {
            promote(progress);
        }
    }

    @Transactional
    public void incrementStreak(UserZoneProgress progress, int clarityScore) {//incrémente la streak
        if (clarityScore >= 8) {
            progress.setConsecutiveHighClarity(
                    progress.getConsecutiveHighClarity() + 1
            );
        } else {
            progress.setConsecutiveHighClarity(0);
        }
    }

    @Transactional
    public void updateHistory(UserZoneProgress progress, int clarityScore) {
        progress.getLastClarityScores().add(clarityScore);

        if (progress.getLastClarityScores().size() > 10) {
            progress.getLastClarityScores().removeFirst();
        }
    }

    @Transactional
    public double average(UserZoneProgress progress) {
        return progress.getLastClarityScores()
                .stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    @Transactional
    public boolean isPromote(UserZoneProgress progress) {
        boolean conditionA = progress.getConsecutiveHighClarity() >= 5;

        boolean conditionB =
                progress.getLastClarityScores().size() == 10
                        && average(progress) >= 8.0;

        return conditionB || conditionA;
    }

    @Transactional
    public void promote(UserZoneProgress progress) {
        progress.setResponsibilityLevel(
                progress.getResponsibilityLevel() + 1
        );

        // Reset des indicateurs (mais PAS du level)
        progress.setConsecutiveHighClarity(0);
        progress.getLastClarityScores().clear();
    }
}
