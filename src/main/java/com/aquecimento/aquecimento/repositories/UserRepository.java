package com.aquecimento.aquecimento.repositories;

import com.aquecimento.aquecimento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
