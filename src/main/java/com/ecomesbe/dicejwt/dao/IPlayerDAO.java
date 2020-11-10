package com.ecomesbe.dicejwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomesbe.dicejwt.dto.Player;

public interface IPlayerDAO extends JpaRepository<Player, Long> {

	Player findPlayerByName(String name);
}
