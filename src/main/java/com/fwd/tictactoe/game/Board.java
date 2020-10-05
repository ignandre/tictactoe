package com.fwd.tictactoe.game;

import java.util.Arrays;

public class Board {
	
	private int boardSize;
	private char[][] grid;
	private int moveCounter;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		this.grid = new char[boardSize][boardSize];
		this.moveCounter = 0;
		
		for(char[] row : grid) {
			Arrays.fill(row, ' ');
		}
	}
	
	public int getBoardSize() {
		return boardSize;
	}
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	public char[][] getGrid() {
		return grid;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	public int getMoveCounter() {
		return moveCounter;
	}
	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	
	public boolean isValidMove(Coordinate coord) {

		return (((coord.getxCoord() < getBoardSize()) && (coord.getxCoord() >= 0)) 
			&& ((coord.getyCoord() < getBoardSize()) && (coord.getyCoord() >= 0))
			&& (grid[coord.getxCoord()][coord.getyCoord()] == ' '));
	}
	
	public void putSymbol(Player player, Coordinate coord) {
		boolean validFlag = isValidMove(coord);
		if(validFlag) {
			grid[coord.getxCoord()][coord.getyCoord()] = player.getPlayerSymbol();
		}
	}
	
	public void printRowLine() {
		System.out.print("\n-------------");
		
		if(grid.length > 3) {
			for(int i = 0; i < grid.length-3; i++) {
				System.out.print("----");
			}
		}
		
		System.out.println();
	}
	
	public void printBoard() {
		
		for(int row = 0; row < grid.length; row++) {
			printRowLine();
			
			for(int col = 0; col < grid[row].length; col++) {
				if(col == 0) {
					System.out.print("| "  + grid[row][col] + " ");
				}
				else if (col == grid[row].length - 1) {
					System.out.print("| " + grid[row][col] + " |");
				}
				else {
					System.out.print("| " + grid[row][col] + " ");
				}
			}
			
			if(row == grid.length-1) {
				printRowLine();
			}
			
		}
		
		
	}
	
	
	public int checkWinner(int row, int col, char player) {
		int result = 0; // 0 - game still ongoing, 1 - win, 2 - tie.
		
		if(grid[row][col] ==  ' ') {
			grid[row][col] = player;
		}
		
		moveCounter++;
		
		for (int i = 0; i < boardSize; i++) {
			
			if(grid[row][i] != player) {
				break;
			}
			if(i == boardSize-1){
				printBoard();
				System.out.println("Player " + player + " wins.");
				result = 1;
			}	
		}
		
		for(int j = 0; j < boardSize; j++) {
			if(grid[j][col] != player) {
				break;
			}
			if(j == boardSize-1) {
				printBoard();
				System.out.println("Player " + player + " wins.");
				result = 1;
			}
		}
			
		if(row == col) {
			for(int i = 0; i < boardSize; i++) {
				if(grid[i][i] != player){
					break;
				}
				if(i == boardSize-1) {
					printBoard();
					System.out.println("Player " + player + " wins.");
					result = 1;
				}
			}
		}
		
		
		if(row + col == boardSize-1) {
			for(int i = 0; i < boardSize; i++) {
				if(grid[i][(boardSize-1)-i] != player) {
					break;
				}
				if(i == boardSize-1) {
					printBoard();
					System.out.println("Player " + player + " wins.");
					result = 1;
				}
			}
		}
		
		if(moveCounter == (Math.pow(boardSize, 2)) && result != 1){
			printBoard();
			System.out.println("TIE");
			result=2;
		}

	   return result;
	}
	
	
}
