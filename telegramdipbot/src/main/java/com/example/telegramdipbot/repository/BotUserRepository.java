package com.example.telegramdipbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.telegramdipbot.model.BotUser;

@Repository
public interface BotUserRepository extends JpaRepository<BotUser, Long> {
	Optional<BotUser> findByChatId(Long chatId);
}