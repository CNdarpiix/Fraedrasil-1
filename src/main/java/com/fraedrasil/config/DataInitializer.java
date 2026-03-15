package com.fraedrasil.config;

import com.fraedrasil.entity.*;
import com.fraedrasil.repository.*;
import com.fraedrasil.entity.Aspect;
import com.fraedrasil.entity.Domain;
import com.fraedrasil.entity.StudyTask;
import com.fraedrasil.entity.StudyZone;
import com.fraedrasil.repository.AspectRepository;
import com.fraedrasil.repository.DomainRepository;
import com.fraedrasil.repository.StudyTaskRepository;
import com.fraedrasil.repository.StudyZoneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner iniData(
            DomainRepository domainRepo,
            AspectRepository aspectRepo,
            StudyZoneRepository zoneRepo,
            StudyTaskRepository taskRepo,
            UserRepository userRepo) {
        return args ->


        {
             /// Domain
             Domain algorithmes = new Domain();
             algorithmes.setName("Monde Algorithmiques");
             domainRepo.save(algorithmes);


              /// Aspects
             Aspect logic = new Aspect();
             logic.setName("Logique algorithmique");
             logic.setDomain(algorithmes);
             aspectRepo.save(logic);

              /// Zones
             StudyZone conditions = new StudyZone();
             conditions.setName("Conditions");
             conditions.setAspect(logic);
            zoneRepo.save(conditions);

              /// Tasks
             StudyTask task = new StudyTask();
             task.setTitle("Identifier condition vraie");
             task.setDescription("Comprendre une condition logique");
            task.setQuestion("Que vaut : 5 > 3 ?");
            task.setDifficulty(1);
            task.setEstimatedMinutes(2);
            task.setStudyZone(conditions);

            taskRepo.save(task);


        };
    }
}
