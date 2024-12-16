package com.example.login.db.repository;

import com.example.login.db.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmailOrUsername(String email, String username);
}