package com.example.bookAppbe.repositories;

import com.example.bookAppbe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepositories extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
