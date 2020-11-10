package com.ecomesbe.dicejwt.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="game")
public class Game {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_dice")
	private int firstDice;
	
	@Column(name="second_dice")
	private int secondDice;
	
	@ManyToOne
	@JsonIgnore
	private Player player;
	
	@Column(name="success")
	private boolean success;

	public Game() {
	}

	public Game(int firstDice, int secondDice) {
		this.firstDice = firstDice;
		this.secondDice = secondDice;
	}

	public Game(int firstDice, int secondDice, boolean success) {
		this.firstDice = firstDice;
		this.secondDice = secondDice;
		this.success = success;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFirstDice() {
		return firstDice;
	}

	public void setFirstDice(int firstDice) {
		this.firstDice = firstDice;
	}

	public int getSecondDice() {
		return secondDice;
	}

	public void setSecondDice(int secondDice) {
		this.secondDice = secondDice;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
