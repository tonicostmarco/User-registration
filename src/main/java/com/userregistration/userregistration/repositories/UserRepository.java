package com.userregistration.userregistration.repositories;

import com.userregistration.userregistration.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
