package com.ecomesbe.dicejwt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomesbe.dicejwt.dao.IPlayerDAO;
import com.ecomesbe.dicejwt.dto.Player;
import com.ecomesbe.dicejwt.service.IPlayerService;

@Service
public class PlayerServiceImpl implements IPlayerService{

	@Autowired
	private IPlayerDAO iPlayerDao;
	
	@Autowired
	private PlayerServiceImpl playerService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	//GET all players
	@Override
	public List<Player> showAllPlayers(){
		return iPlayerDao.findAll();
	}
	
	
	@Override
	public Player editPlayer(Player player) {
		return null;
	}
	//POST save new player
	//@Override
	public Player saveNewPlayer(String name, String password) {
		
		Player player = new Player(name, password);
		
		if(name=="") {
			player.setName("ANÒNIM");
		}else {
			
			//Check that it does not exist in database
			List<Player> allPlayers = new ArrayList<Player>();
			allPlayers = playerService.showAllPlayers();
			int total = 0;
			for(int i=0; i<allPlayers.size(); i++) {
				if(name.equals(allPlayers.get(i).getName())) {
					throw new IllegalArgumentException("This player already exists in the database!");
				}else {
					total++;
				}
			}
			if(total == allPlayers.size()) {
				player.setName(name);
				player.setPassword(bCryptPasswordEncoder.encode(password));
			}
		}
		
		return iPlayerDao.save(player);
	}
	
	//PUT change a player of the database
	public Player editPlayer(String name, Long id) {
		
		Optional<Player> playerOptional = iPlayerDao.findById(id);
		
		//check if exist
		if(playerOptional.isPresent()) {
			Player playerEdit = playerOptional.get();
			
			if(name=="") {
				playerEdit.setName("ANÒNIM");
			}else {
				playerEdit.setName(name);
			}
			
			return iPlayerDao.save(playerEdit);
		}else {
			throw new IllegalArgumentException("This player not exist!");
		}
	}
	
	@Override
	public HashMap<String, Object> getAllSuccess() {
		
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			List<Player> playersList = iPlayerDao.findAll();
			float average = (float)0;
			int count = 0;
			for(Player playerFor : playersList) {
				average+=playerFor.getSuccess();
				count++;
			}
			map.put("success", true);
			map.put("Success average: ", average/count);
		}catch(Exception e) {
			map.put("success",  false);
			map.put("message: ", e.getMessage());
		}
		
		return map;
	}
	
	@Override
	public HashMap<String, Object> getWinner(){
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Player> playersList = iPlayerDao.findAll();
			Player playerMostWinner = new Player();
			boolean first = true;
			for(Player playerFor : playersList) {
				if(first) {
					playerMostWinner = playerFor;
					first = false;
				}else {
					if(playerMostWinner.getSuccess() < playerFor.getSuccess()) {
						playerMostWinner = playerFor;
					}
				}
			}
			map.put("success", true);
			map.put("Player most successful: ", playerMostWinner);
		}catch(Exception e) {
			map.put("success", false);
			map.put("message: ", e.getMessage());
		}
		return map;
	}
	
	@Override
	public HashMap<String, Object> getLoser(){
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Player> playersList = iPlayerDao.findAll();
			Player playerMostLoser = new Player();
			boolean first = true;
			for(Player playerFor : playersList) {
				if(first) {
					playerMostLoser = playerFor;
					first = false;
				}else {
					if(playerMostLoser.getSuccess() > playerFor.getSuccess()) {
						playerMostLoser = playerFor;
					}
				}
			}
			map.put("success", true);
			map.put("Player most loser: ", playerMostLoser);
		}catch(Exception e) {
			map.put("success", false);
			map.put("message: ", e.getMessage());
		}
		return map;
	}
}
