package com.calendar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;

@Configuration
@Profile({"heroku", "heroku-dev"})
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String fullDbUrl;

    @Bean
    public DataSource dataSource() {
        /*Possibly not best pactice!
        Look for the first "?" in the SPRING_DATASOURCE_URL environment variable, which denotes the end of the db
        credentials in the full string. The resulting string will only contain the hostname, port and the database name.
         */
        int firstUnwantedChar = fullDbUrl.indexOf("?");
        String dbUrl = fullDbUrl.substring(0, firstUnwantedChar);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername("${spring.datasource.user}");
        config.setPassword("${spring.datasource.password}");
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }
}
