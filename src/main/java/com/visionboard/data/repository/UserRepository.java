package com.visionboard.data.repository;

import com.visionboard.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String > {

    Optional<User> findByEmail(String email);
    User findByName(String name);
}
