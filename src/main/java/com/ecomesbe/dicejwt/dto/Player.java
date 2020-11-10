package com.ecomesbe.dicejwt.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="player",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	List<Game> games = new ArrayList<>();
	
	@Column(name="date")
	private LocalDate date = LocalDate.now();
	
	//@JsonIgnore
	@Column(name="success")
	private float success;
	
	@JsonIgnore
	@Column(name="game_win")
	private int gameWin;
	
	@JsonIgnore
	@Column(name="game_lose")
	private int gameLose;
	
	@JsonIgnore
	@Column(name="game_total")
	private int gameTotal;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	public Player() {
	}

	public Player(Long id, String name, List<Game> games, LocalDate date, float success) {
		this.id = id;
		this.name = name;
		this.games = games;
		this.date = date;
		this.success = success;
	}

	public Player(Long id, String name, List<Game> games, LocalDate date) {
		this.id = id;
		this.name = name;
		this.games = games;
		this.date = date;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, String password) {
		this.name=name;
		this.password=password;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getSuccess() {
		return success;
	}

	public void setSuccess(float success) {
		this.success = success;
	}

	public int getGameWin() {
		return gameWin;
	}

	public void setGameWin(int gameWin) {
		this.gameWin = gameWin;
	}

	public int getGameLose() {
		return gameLose;
	}

	public void setGameLose(int gameLose) {
		this.gameLose = gameLose;
	}

	public int getGameTotal() {
		return gameTotal;
	}

	public void setGameTotal(int gameTotal) {
		this.gameTotal = gameTotal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
