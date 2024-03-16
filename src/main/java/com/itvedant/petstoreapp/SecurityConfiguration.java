package com.itvedant.petstoreapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.itvedant.petstoreapp.services.MyUserDetailsService;

@Configuration
@EnableMethodSecurity(
    prePostEnabled = true,
    jsr250Enabled =  true,
    securedEnabled = true
)
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception{
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/register/**")
            .permitAll()
            .requestMatchers(HttpMethod.GET,"/products/**")
            .permitAll()
            .requestMatchers(HttpMethod.POST,"/products/**")
            .permitAll()
            .requestMatchers("/users/**")
            .hasRole("HR")
            .requestMatchers("/orderses/**")
            .hasRole("IT")
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(this.passwordEncoder());
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users(){
        UserDetails user1 = User.withUsername("Harry")
                                .password(passwordEncoder().encode("abc"))
                                .roles("HR")
                                .build();

        UserDetails user2 = User.withUsername("Mike")
                                .password(passwordEncoder().encode("xyz"))
                                .roles("IT")
                                .build();
        UserDetails user3 = User.withUsername("Scott")
                                .password(passwordEncoder().encode("abc"))
                                .roles("HR")
                                .build();

        UserDetails user4 = User.withUsername("Smith")
                                .password(passwordEncoder().encode("xyz"))
                                .roles("IT")
                                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);

    }
}
