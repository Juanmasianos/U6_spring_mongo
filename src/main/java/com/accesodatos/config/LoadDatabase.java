package com.accesodatos.config;

import com.accesodatos.entity.Role;
import com.accesodatos.entity.UserEntity;
import com.accesodatos.entity.Product; // Asegúrate de importar tu entidad Product
import com.accesodatos.repository.RoleRepository;
import com.accesodatos.repository.UserRepository;
import com.accesodatos.repository.ProductRepository; // Asegúrate de importar tu repositorio
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, 
                                   PasswordEncoder passwordEncoder, 
                                   RoleRepository roleRepository,
                                   ProductRepository productRepository) { 

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
                                .isEnabled(true)
                                .accountNonExpired(true)
                                .accountNonLocked(true)
                                .credentialsNonExpired(true)
                                .build()
                ));
            }

            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                        Product.builder()
                                .name("Portátil Gaming X")
                                .description("Procesador de última generación, 16GB RAM, 1TB SSD, RTX 4060")
                                .price(1199.99)
                                .stock(15)
                                .build(),
                        Product.builder()
                                .name("Ratón Ergonómico Inalámbrico")
                                .description("Conexión 2.4GHz y Bluetooth, sensor óptico de alta precisión")
                                .price(45.50)
                                .stock(50)
                                .build(),
                        Product.builder()
                                .name("Teclado Mecánico RGB")
                                .description("Switches de respuesta rápida, retroiluminación configurable")
                                .price(89.90)
                                .stock(30)
                                .build(),
                        Product.builder()
                                .name("Monitor 27 Pulgadas 4K")
                                .description("Panel IPS, tasa de refresco de 144Hz, ideal para diseño y gaming")
                                .price(349.00)
                                .stock(10)
                                .build(),
                        Product.builder()
                                .name("Auriculares Cancelación Ruido")
                                .description("Auriculares inalámbricos de diadema con cancelación activa de ruido")
                                .price(125.00)
                                .stock(25)
                                .build()
                ));
            }
        };
    }
}