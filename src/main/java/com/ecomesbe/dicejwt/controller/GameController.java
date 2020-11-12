package com.ecomesbe.dicejwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomesbe.dicejwt.dto.Game;
import com.ecomesbe.dicejwt.service.IGameService;

@RestController
@RequestMapping("/dicegame")
public class GameController {
	
	@Autowired
	IGameService gameService;
	
	//POST /players/{id}/games/ : un jugador específic realitza una tirada dels daus.
	@PostMapping("/players/{id}/games")
	public ResponseEntity<Object> playGame (@PathVariable("id") long player){
		try {
			return ResponseEntity.ok().body(gameService.newGame(player));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
	
	//DELETE /players/{id}/games: elimina les tirades del jugador
	@DeleteMapping("/players/{id}/games")
	public ResponseEntity<Object> deleteAllGames (@PathVariable("id") long player){
		try {
			gameService.deleteAllGamesByPlayer(player);
			return ResponseEntity.ok().build();			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}
	
	//GET /players/{id}/games: retorna el llistat de jugades per un jugador.
	@GetMapping("/players/{id}/games")
	public ResponseEntity<List<Game>> showAllGames (@PathVariable("id") long player){
		return ResponseEntity.ok().body(gameService.findGamesByPlayer(player));
	}
}
