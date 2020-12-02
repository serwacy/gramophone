package com.gruzini.gramophone.security

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/graphql").permitAll()
                .antMatchers("/graphiql-ui").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
        http.formLogin()
                .defaultSuccessUrl("/graphiql-ui", true)
        http.csrf().disable()
                .headers().frameOptions().disable()
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth)
    }
}
