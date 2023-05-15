package com.example.restapiproject.repository;

import com.example.restapiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByIdAndInActive(long id,String inactive);
}
