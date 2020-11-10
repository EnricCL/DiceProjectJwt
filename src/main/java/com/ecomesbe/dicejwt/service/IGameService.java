package com.ecomesbe.dicejwt.service;

import java.util.List;

import com.ecomesbe.dicejwt.dto.Game;

public interface IGameService {

	//Delete all games of a player
	void deleteAllGamesByPlayer(Long player);
	
	//View all games of a player
	List<Game> findGamesByPlayer(Long player);
	
	//New Game
	Game newGame(Long player);
}
