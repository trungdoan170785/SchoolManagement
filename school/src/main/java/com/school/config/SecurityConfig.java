package com.school.config;

import com.school.entity.Role;
import com.school.entity.User;
import com.school.repository.RoleRepository;
import com.school.repository.UserRepository;
import com.school.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Bean
    public CommandLineRunner dataLoader(BCryptPasswordEncoder encoder){

        return args -> {

            if(roleRepo.count() == 0){
                roleRepo.save(new Role(null,"ROLE_ADMIN"));
                roleRepo.save(new Role(null,"ROLE_STAFF"));
                roleRepo.save(new Role(null,"ROLE_TEACHER"));
                roleRepo.save(new Role(null,"ROLE_ACCOUNTANT"));
            }

            if(userRepo.count() == 0){

                User admin = User.builder()
                        .username("admin")
                        .password(encoder.encode("123"))
                        .enabled(true)
                        .roles(Set.of())
                        .build();

                userRepo.save(admin);
            }
        };
    }

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authProvider())   // 🔥 đảm bảo dùng provider này
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/adminlte/**").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/teachers/**").hasRole("ADMIN")
                        .requestMatchers("/students/**").hasAnyRole("ADMIN","STAFF")
                        .requestMatchers("/payments/**").hasAnyRole("ADMIN","ACCOUNTANT")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}
