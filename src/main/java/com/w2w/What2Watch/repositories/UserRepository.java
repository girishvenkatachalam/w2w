package com.w2w.What2Watch.repositories;

import com.w2w.What2Watch.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

     public List<User> findByNameIgnoreCase(String name);
     public User findByEmail(String email);

}
