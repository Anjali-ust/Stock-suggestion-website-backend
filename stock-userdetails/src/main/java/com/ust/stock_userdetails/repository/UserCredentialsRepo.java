package com.ust.stock_userdetails.repository;


import com.ust.stock_userdetails.Model.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepo extends MongoRepository<UserCredentials,String> {
    Optional<UserCredentials> findByUsername(String username);

}
