package com.brandy.courses;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ApplicationConfig {
    @Bean
//    @Profile("test")
    @Qualifier("bean1")
    public MyFirstApplication myFirstBean() {
        return new MyFirstApplication("First Bean");
    }

    @Bean
//    @Profile("dev")
    @Qualifier("bean2")
    public MyFirstApplication mySecondBean() {
        return new MyFirstApplication("Second Bean");
    }

    @Bean
    @Primary
    public MyFirstApplication myThirdBean() {
        return new MyFirstApplication("Third Bean");
    }
}
