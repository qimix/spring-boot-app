package ru.netology.spring_boot_app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import ru.netology.spring_boot_app.profiles.DevProfile;
import ru.netology.spring_boot_app.profiles.ProductionProfile;
import ru.netology.spring_boot_app.profiles.SystemProfile;

public class JavaConfig {
    @Bean
    @ConditionalOnProperty
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
