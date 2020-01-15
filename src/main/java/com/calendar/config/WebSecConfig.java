package com.calendar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.calendar.config.error.CustomAccessDeniedHandler;
import com.calendar.config.security.MySavedRequestAwareAuthenticationSuccessHandler;
import com.calendar.config.security.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
)

public class WebSecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	protected void configure(HttpSecurity http) throws Exception {
	    http
	    	.csrf().disable()
	    	.cors()
	    	.and()
	    	.authorizeRequests()
	    		.antMatchers(
                    "/login", 
                    "/registration"
                    )
	    		.permitAll()
	    		.and()
	    	.formLogin()
            	.successHandler(mySuccessHandler)
            	.failureHandler(myFailureHandler)
	    		.loginPage("/login")
	    		.permitAll()
	    		.and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
	    	.and()
	    	.logout()
	    		.permitAll();
	}
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
