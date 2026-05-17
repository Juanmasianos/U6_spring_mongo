package com.accesodatos.config;

import com.accesodatos.handlers.JwtAccessDeniedHandler;
import com.accesodatos.handlers.JwtAuthenticationEntryPoint;
import com.accesodatos.jwt.JwtAuthenticationFilter;
import com.accesodatos.jwt.JwtTokenProvider;
import com.accesodatos.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
* UserDetails: representa a un usuario de Spring Sec, es una interfaz.
*
* AuthenticationProvider: Permite acceder al usuario en la BD. Se puede sobreescribir esta clase para
* implementar la seguridad de forma personalizada o proporcionandola como un Bean.
* Tiene un metodo llamado authentication que sobreescribitemos.
*
* UserDetailService: que tiene un metodo denominado loadUserByUsername() que será implementado
*
*
*
*/

@EnableWebSecurity(debug = true)
@EnableMethodSecurity
@Configuration
public class SecurityConfig {



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            JwtAuthenticationFilter jwtAuthenticationFilter,
                                            JwtAuthenticationEntryPoint entryPoint,
                                            JwtAccessDeniedHandler accessDeniedHandler) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/v3/doc",
                                                "/v3/doc/**",
                                                "/doc/swagger-ui/**",
                                                "/doc/swagger-ui.html")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/instructors/ping")
                                    .hasAnyRole("INVITED", "ADMIN", "DEVELOPER", "USER")
                                .requestMatchers(HttpMethod.GET,"/api/v1/courses/ping").hasRole("INVITED")
                                .requestMatchers(HttpMethod.GET,"/api/v1/students/ping").hasRole("INVITED")
                                .requestMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "DEVELOPER", "USER")
                                .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/**").hasAnyRole("ADMIN", "DEVELOPER")
                                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    PasswordEncoder passwordEncoder() {

        // Sin encriptacion
        // return NoOpPasswordEncoder.getInstance();

        return new BCryptPasswordEncoder();

    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                                    UserDetailsServiceImpl userDetailsService) {

        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);

    }


}
