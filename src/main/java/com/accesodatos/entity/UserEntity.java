package com.accesodatos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserEntity {

    @Id
    private UUID id;

    @Field
    private String username;
    @Field
    private String password;

    @Field    
    private boolean isEnabled;

    @Field
    private boolean accountNonExpired;

    @Field
    private boolean accountNonLocked;

    @Field
    private boolean credentialsNonExpired;

}
