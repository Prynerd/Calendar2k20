package com.calendar.Connection;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("heroku")
public class HerokuConnection extends DBConnection {

    public HerokuConnection() {
        this.url ="jdbc:postgres://ec2-54-228-250-82.eu-west-1.compute.amazonaws.com:5432/d6981inv0knfbm";
        this.user = "aywfgexrcvohxc";
        this.password = "a31ceef08451da1c37651478c56b94711b57f140c7c553770a4c1a065aa9c29c";
    }
}
