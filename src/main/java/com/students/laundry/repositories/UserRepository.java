package com.students.laundry.repositories;

import com.students.laundry.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPassNumber(String passNumber);

    void deleteByPassNumber(String passNumber);
}
