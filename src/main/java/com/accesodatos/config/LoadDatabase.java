package com.accesodatos.config;

import com.accesodatos.entity.Role;
import com.accesodatos.entity.UserEntity;
import com.accesodatos.repository.RoleRepository;
import com.accesodatos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {

        return args -> {

            Role roleCustomer = roleRepository.findByName("CUSTOMER").orElse(null);
            if (roleCustomer == null) {
                roleCustomer = Role.builder().name("CUSTOMER").build();
                roleRepository.save(roleCustomer);

                            userRepository.saveAll(List.of(
                    UserEntity.builder()
                            .username("customer")
                            .password(passwordEncoder.encode("customer"))
                            .roles(Set.of(roleCustomer))
                            .build()
            ));
            }

        };
    }
}