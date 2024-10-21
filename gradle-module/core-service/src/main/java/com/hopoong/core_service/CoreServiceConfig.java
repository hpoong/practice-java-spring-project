package com.hopoong.core_service;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing // CreatedDate LastModifiedDate
@ComponentScan
@EnableAutoConfiguration
public class CoreServiceConfig {

    private final Environment environment;

    public CoreServiceConfig(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void printActiveProfiles() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            System.out.println("No active profiles");
        } else {
            System.out.println("Active Profiles in CoreConfiguration: " + String.join(", ", activeProfiles));
        }
    }

}
