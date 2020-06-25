package com.calendar.Connection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("heroku")
public class HerokuConnection extends DBConnection {

    public HerokuConnection() {
        this.url ="jdbc:postgres://ec2-54-217-225-16.eu-west-1.compute.amazonaws.com:5432/d3o1d5kf321dvj";
        this.user = "xeuawbcnsogepg";
        this.password = "d208c8ba3d208859d0aee1e30e5bb6a640a846eda52c844d94bd04e61cbcaa57";
    }
}
