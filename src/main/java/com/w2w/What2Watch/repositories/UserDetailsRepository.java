package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDetailsRepository extends MongoRepository<UserDetails,String> {

    public List<UserDetails> findByEmailId(String email);
}
