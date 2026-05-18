/*package com.accesodatos.service.impl;

import com.accesodatos.dto.auth.AuthRegisterRequest;
import com.accesodatos.dto.auth.AuthRegisterResponse;
import com.accesodatos.entity.Role;
import com.accesodatos.entity.UserEntity;
import com.accesodatos.repository.RoleRepository;
import com.accesodatos.repository.UserRepository;
import com.accesodatos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AuthRegisterResponse register(AuthRegisterRequest authRegisterRequest) {

        UserEntity userEntity = UserEntity.builder()
                .username(authRegisterRequest.getUsername())
                .password(passwordEncoder.encode(authRegisterRequest.getPassword()))
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        Set<Role> roles = new HashSet<>();

        Set<String> roleNames = Optional.ofNullable(authRegisterRequest.getRoles()).orElse(Set.of());

        for (String roleName : roleNames) {

            Optional<Role> role = roleRepository.findByName(roleName);

            roles.add(role.orElseThrow(() -> new IllegalArgumentException("Role " + roleName + " not found")));

        }

        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        Set<String> role2 = userEntity
                .getRoles()
                .stream()
                .map(role -> "ROLE_".concat(role.getName()))
                .collect(Collectors.toSet());

        return new AuthRegisterResponse(authRegisterRequest.getUsername(), role2);



    }
}
*/