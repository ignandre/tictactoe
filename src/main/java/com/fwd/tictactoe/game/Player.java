package com.fwd.tictactoe.game;

public class Player {
	private char playerSymbol;
	private Board board;
	
	public Player(char playerSymbol, Board board) {
		this.playerSymbol = playerSymbol;
		this.board = board;
	}
	
	public char getPlayerSymbol() {
		return playerSymbol;
	}

	public void setPlayerSymbol(char playerSymbol) {
		this.playerSymbol = playerSymbol;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
