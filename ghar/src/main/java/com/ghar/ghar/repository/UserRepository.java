package com.ghar.ghar.repository;

import com.ghar.ghar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
