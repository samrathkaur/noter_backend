package com.example.minor.Repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.minor.Model.User;
public interface UserRepository extends MongoRepository<User, String> {
    Optional <User> findByEmail(String email);
    Boolean existsByEmail(String email);
     Boolean existsByName(String name);
}
