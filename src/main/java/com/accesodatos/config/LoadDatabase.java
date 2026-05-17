package com.accesodatos.config;

import com.accesodatos.entity.Role;
import com.accesodatos.entity.UserEntity;
import com.accesodatos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

//@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            Role roleAdmin = Role.builder().name("ADMIN").build();
            Role roleUser = Role.builder().name("USER").build();
            Role roleInvited = Role.builder().name("INVITED").build();
            Role roleDevelop = Role.builder().name("DEVELOPER").build();

            UserEntity userAlex = UserEntity.builder()
                    .username("Alex")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();
            UserEntity userJose = UserEntity.builder()
                    .username("Jose")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleUser))
                    .build();
            UserEntity userDaniel = UserEntity.builder()
                    .username("Daniel")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();
            UserEntity userAndres = UserEntity.builder()
                    .username("Andres")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .roles(Set.of(roleDevelop))
                    .build();

            userRepository.saveAll(List.of(userAlex, userJose, userDaniel, userAndres));
        };

    }
}
