package com.ecomesbe.dicejwt.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomesbe.dicejwt.dao.IGameDAO;
import com.ecomesbe.dicejwt.dao.IPlayerDAO;
import com.ecomesbe.dicejwt.dto.Game;
import com.ecomesbe.dicejwt.dto.Player;
import com.ecomesbe.dicejwt.service.IGameService;



@Service
public class GameServiceImpl implements IGameService{

	@Autowired
	IGameDAO gameDao;
	
	@Autowired
	IPlayerDAO playerDao;
	
	@Override
	public void deleteAllGamesByPlayer(Long player) {
		List<Game> listGames = gameDao.findGamesByPlayerId(player);
		gameDao.deleteAll(listGames);
	}
	
	@Override
	public List<Game> findGamesByPlayer(Long player){
		Optional<Player> optionalPlayer = playerDao.findById(player);
		if(optionalPlayer.isPresent()) {
			return gameDao.findGamesByPlayerId(player);
		}else {
			throw new IllegalArgumentException("This id not exist");
		}
	}
	
	@Override
	public Game newGame(Long player) {
		
		int firstDice = ThreadLocalRandom.current().nextInt(1, 7);
		int secondDice = ThreadLocalRandom.current().nextInt(1, 7);
		boolean success = false;
		if((firstDice + secondDice)==7) {
			success=true;
		}
		
		Game newGame = new Game(firstDice, secondDice, success);
		
		Optional<Player> actualPlayer = playerDao.findById(player);
		
		if(actualPlayer.isPresent()) {
			
			Player playerToSet = actualPlayer.get();
			playerToSet.setGameTotal(actualPlayer.get().getGameTotal() + 1);
			
			if(success) {
				playerToSet.setGameWin(actualPlayer.get().getGameWin() + 1);
			}else {
				playerToSet.setGameLose(actualPlayer.get().getGameLose() + 1);
			}
			
			if(actualPlayer.get().getGameWin() > 0) {
				playerToSet.setSuccess((float)actualPlayer.get().getGameWin() / (float)actualPlayer.get().getGameTotal() * 100);
			}
			
			newGame.setPlayer(playerToSet);
			playerDao.save(playerToSet);
			return gameDao.save(newGame);
			
		}else {
			throw new IllegalArgumentException("This id not exist");
		}
	}
}
