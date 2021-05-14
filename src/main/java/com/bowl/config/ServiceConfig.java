package com.bowl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
/*@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching*/
@ComponentScan("com.bowl.service")
public class ServiceConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void afterService () {
        System.out.println("Service classe générée");
    }
}
