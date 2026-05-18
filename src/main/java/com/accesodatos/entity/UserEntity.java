package com.accesodatos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String id;

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
