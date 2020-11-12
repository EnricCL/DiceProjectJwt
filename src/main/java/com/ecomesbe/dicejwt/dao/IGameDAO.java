package com.ecomesbe.dicejwt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomesbe.dicejwt.dto.Game;


@Repository
public interface IGameDAO extends JpaRepository<Game, Long>{

	List<Game> findGamesByPlayerId (Long playerId);
}
