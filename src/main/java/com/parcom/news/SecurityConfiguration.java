package com.parcom.news;


import com.parcom.security_client.AuthenticationTokenProcessingFilter;
import com.parcom.security_client.UnauthorizedEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final  UnauthorizedEntryPoint unauthorizedEntryPoint;

    private final AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

    public SecurityConfiguration(UnauthorizedEntryPoint unauthorizedEntryPoint, AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter) {
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
        this.authenticationTokenProcessingFilter = authenticationTokenProcessingFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/springfox-swagger-ui/**",
                "/swagger-ui.html/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                 "/health",
                "/add/**").
                 permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .addFilterBefore(authenticationTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class);

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }


}