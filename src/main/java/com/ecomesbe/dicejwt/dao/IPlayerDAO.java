package com.ecomesbe.dicejwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomesbe.dicejwt.dto.Player;

@Repository
public interface IPlayerDAO extends JpaRepository<Player, Long> {

	Player findPlayerByName(String name);
}
