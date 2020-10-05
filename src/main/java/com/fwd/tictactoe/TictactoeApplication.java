package com.fwd.tictactoe;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fwd.tictactoe.game.Board;
import com.fwd.tictactoe.game.Coordinate;
import com.fwd.tictactoe.game.Player;

@SpringBootApplication
public class TictactoeApplication implements CommandLineRunner{
	private  static Logger logger = LoggerFactory.getLogger(TictactoeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		Scanner in = new Scanner(System.in);
		String input = "";
		int size = 0;
		
		while(true) {
			System.out.println("Enter a number for the size of the game board (N x N): ");
			input = in.nextLine();
			input = input.replaceAll("[^\\d]", "");
			
			if(!input.isEmpty() && Integer.parseInt(input) >= 3) {
				size = Integer.parseInt(input);
				break;
			}
			else {
				if(size < 3) {
					System.out.println("Game board size must be minimum of 3");
				}
			}
		}
		
		Board board = new Board(size);
		play(board);
	}
	
	
	private void play(Board board) {
		Player playerX = new Player('X', board);
		Player playerO = new Player('O', board);
		
		Scanner in = new Scanner(System.in);
		
		int turnCounter = 0;
		Player currentPlayer = null;
		String winPlayer;
		int gameState = 0;
		
		String move;
		
		while(true) {
			turnCounter++;
			
			if(turnCounter % 2 == 0) {
				currentPlayer = playerX;
			}
			else {
				currentPlayer = playerO;
			}
			
			board.printBoard();
			
			System.out.println("Turn " + turnCounter + ": Player " + currentPlayer.getPlayerSymbol() + "'s turn.");
			System.out.println("Enter move coordinate - 2 digits representing row and column separated by a comma (e.g. 0,2): ");
			move = in.nextLine();
			
			
			Coordinate coord = parseInput(move, board);
			
			while(coord == null) {
				System.out.println("Invalid coordinate. Please re-enter valid move coordinate - 2 digits representing row and column separated by a comma (e.g. 1,2): ");
				move = in.nextLine();
				coord = parseInput(move, board);
			}

			gameState = board.checkWinner(coord.getxCoord(), coord.getyCoord(), currentPlayer.getPlayerSymbol());
					
			
			if(gameState == 1 || gameState == 2) {
				break;
			}
			
			
		}
		
	}

	public Coordinate parseInput(String move, Board board) {

		Coordinate coord;
		
		move = move.replaceAll("[^\\d,]", "");

		String[] moveCoord = move.split(",");
		
		if(moveCoord.length == 2 && moveCoord[0].matches("[0-9]+") && moveCoord[1].matches("[0-9]+")) {
			int row = Integer.parseInt(moveCoord[0]);
			int col = Integer.parseInt(moveCoord[1]);
			
			coord = new Coordinate(row, col);
			
			while(!board.isValidMove(coord)) {
				board.printBoard();
				System.out.println("Invalid coordinate. Please re-enter valid move coordinate - 2 digits representing row and column separated by a comma (e.g. 1,2): ");
				Scanner in = new Scanner(System.in);
				move = in.nextLine();
				
				coord = parseInput(move, board);
			}
			
			return coord;
		}
			
		return null;
	}

}
