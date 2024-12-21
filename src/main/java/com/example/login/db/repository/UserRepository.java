package com.example.login.db.repository;

import com.example.login.db.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmailOrUsername(String email, String username);
    List<UserEntity> findAll();
}