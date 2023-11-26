package com.ghar.ghar.repository;

import com.ghar.ghar.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
