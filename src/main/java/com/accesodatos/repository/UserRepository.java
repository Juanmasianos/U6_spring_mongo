package com.accesodatos.repository;

import com.accesodatos.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
