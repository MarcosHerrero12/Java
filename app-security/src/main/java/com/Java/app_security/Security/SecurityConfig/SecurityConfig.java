package com.Java.app_security.Security.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig { //configuracion por default es esta
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("welcome/loans", "welcome/balance", "/welcom/account", "welcome/cust").authenticated()
                                .anyRequest().permitAll())//cualquier request que sea mandado debo tener autenticacion
                .formLogin(Customizer.withDefaults())//para el form del login
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
/*    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){ //customizados usuarios en memoria
        UserDetails admin = User.withUsername("Admin")
                .password("MarcosHerrero")
                .authorities("ADMIN")
                .build();

        UserDetails user = User.withUsername("User")
                .password("MarcosHerrero")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/
//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource){//con Jdbc, datasource en properties
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
