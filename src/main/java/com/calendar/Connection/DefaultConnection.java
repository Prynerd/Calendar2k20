package com.calendar.Connection;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DefaultConnection extends DBConnection implements EnvironmentAware {

    public DefaultConnection() {
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.url = environment.getProperty("spring.datasource.url");
        this.user = environment.getProperty("spring.datasource.username");
        this.password = environment.getProperty("spring.datasource.password");
    }
}
