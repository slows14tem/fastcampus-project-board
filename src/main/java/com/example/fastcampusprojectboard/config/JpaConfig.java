package com.example.fastcampusprojectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        //Article의 createdby에 자동으로 넣어진다.
        return () -> Optional.of("kang");   //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때 수정 필요
    }
}
