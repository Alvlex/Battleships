package Battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);
	int boardSize = 10;
	String[][] board = new String[boardSize][boardSize];
	Ships[] twoShips = new Ships[4];
	Ships[] threeShips = new Ships[3];
	Ships[] fourShips = new Ships[2];
	Ships[] fiveShip = new Ships[1];
	//-------------------------------------------------------------------------------
	void setUpBoard(){
		for (int i = 0; i < boardSize; i ++){
			for (int j = 0; j < boardSize; j ++){
				board[i][j] = " ";
			}
		}
	}
	//----------------------------------------------------------------------------
	int menu(){
		System.out.println("Menu\n(1)Play a normal game\n(2)Play a test game");
		return scanner.nextInt();
	}
	//-----------------------------------------------------------------------------
	void setUpGame(){
		setUpShips(twoShips, 2, 0);
		setUpShips(threeShips, 3, 4);
		setUpShips(fourShips, 4, 7);
		setUpShips(fiveShip, 5, 9);
	}
	//-----------------------------------------------------------------------------
	void setUpShips(Ships[] ships, int size, int symbol){
		for (int i = 0; i < ships.length; i ++){
			ships[i] = new Ships();
			ships[i].size = size;
			ships[i].coordinates = new Coordinates[ships[i].size];
			randomGeneration(ships[i]);
			ships[i].symbol = (symbol + i) + ""; // 0 - 3 = size two ships on the board, 4 - 6 = size three ships, 7 - 8 = size four ships, 9 = size five ship
			updateBoardWithShips(ships[i]);
		}
	}
	//-----------------------------------------------------------------------------
	void randomGeneration(Ships ship){
		String direction = "";
		int tempyCoordinate, tempxCoordinate;
		boolean loop = false;
		while(true){
			tempxCoordinate = random.nextInt(10);
			tempyCoordinate = random.nextInt(10);
			direction = pickDirection(tempxCoordinate, tempyCoordinate, ship.size);
			for (int i = 0; i < ship.size; i ++){
				ship.coordinates[i] = new Coordinates();
				switch(direction){
				case "UP":
					if (checkSquare(tempxCoordinate, tempyCoordinate - i)){
						ship.coordinates[i].xCoordinate = tempxCoordinate;
						ship.coordinates[i].yCoordinate = tempyCoordinate - i;
						loop = false;
					}
					else{
						loop = true;
					}
					break;
				case "DOWN":
					if (checkSquare(tempxCoordinate, tempyCoordinate + i)){
						ship.coordinates[i].xCoordinate = tempxCoordinate;
						ship.coordinates[i].yCoordinate = tempyCoordinate + i;
						loop = false;
					}
					else{
						loop = true;
					}
					break;
				case "LEFT":
					if (checkSquare(tempxCoordinate - i, tempyCoordinate)){
						ship.coordinates[i].xCoordinate = tempxCoordinate - i;
						ship.coordinates[i].yCoordinate = tempyCoordinate;
						loop = false;
					}
					else{
						loop = true;
					}
					break;
				case "RIGHT":
					if (checkSquare(tempxCoordinate + i, tempyCoordinate)){
						ship.coordinates[i].xCoordinate = tempxCoordinate + i;
						ship.coordinates[i].yCoordinate = tempyCoordinate;
						loop = false;
					}
					else{
						loop = true;
					}
					break;
				}
				if (loop == true){
					break;
				}
			}
			if (loop == false){
				break;
			}
		}
	}
	//--------------------------------------------------------------------------------
	boolean checkSquare(int x, int y){
		if (board[x][y].equals(" ")){
			return true;
		}
		else{
			return false;
		}
	}
	//-------------------------------------------------------------------------------
	String pickDirection(int X, int Y, int size){
		List<String> directions = new ArrayList<String>();
		directions.add("UP");
		directions.add("DOWN");
		directions.add("LEFT");
		directions.add("RIGHT");
		int direction = 0;
		while (true){
			direction = random.nextInt(directions.size());
			if (checkDirection((String) directions.get(direction), X, Y, size)){
				return (String) directions.get(direction);
			}
			else{
				directions.remove(directions.get(direction));
			}
			if (directions.size() == 0){
				return "";
			}
		}
	}
	//-------------------------------------------------------------------------------
	boolean checkDirection(String direction, int X, int Y, int size){
		if (direction.equals("UP")){
			if (Y - size < 0){
				return false;
			}
			else{
				return true;
			}
		}
		else if (direction.equals("DOWN")){
			if (Y + size >= boardSize){
				return false;
			}
			else{
				return true;
			}
		}
		else if (direction.equals("LEFT")){
			if (X - size < 0){
				return false;
			}
			else{
				return true;
			}
		}
		else if (direction.equals("RIGHT")){
			if (X + size >= boardSize){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}
	//-------------------------------------------------------------------------------
	void updateBoardWithShips(Ships ship){
		for (int i = 0; i < ship.size; i ++){
			board[ship.coordinates[i].xCoordinate][ship.coordinates[i].yCoordinate] = ship.symbol;
		}
	}
	//-------------------------------------------------------------------------------
	void updateBoardWithGuesses(int[] guess, boolean hit){
		if (hit){
			board[guess[0]][guess[1]] = "X";
		}
		else{
			board[guess[0]][guess[1]] = "O";
		}
	}
	//-------------------------------------------------------------------------------
	void displayBoard(){
		List<String> sunkShipsSymbols = new ArrayList<String>();
		System.out.println("\n");
		System.out.print("  |A|B|C|D|E|F|G|H|I|J|");
		for (int i = 0; i < boardSize; i ++){
			System.out.println();
			makeDashes();
			if (!(i == 9)){
				System.out.print((i + 1) + " |");
			}
			else{
				System.out.print((i + 1) + "|");
			}
			for (int j = 0; j < boardSize; j ++){
				if (!board[j][i].equals(" ") && !board[j][i].equals("O") && !board[j][i].equals("X")){
					System.out.print(" |");
				}
				else{
					sunkShipsSymbols = getSunkShips();
					//STILL NEED TO FINISH THIS SO THAT SUNK SHIP SYMBOLS ARE DISPLAYED ON THE BOARD (E.G. '0' OR '1' OR '9')
							System.out.print(board[j][i] + "|");
				}
			}
		}
		System.out.println("\n");
	}
	//-------------------------------------------------------------------------------
	List<String> getSunkShips(){
		List<String> symbols = new ArrayList<String>();
		for (int i = 0; i < twoShips.length; i ++){
			if (twoShips[i].isSunk == true){
				symbols.add(twoShips[i].symbol);
			}
		}
		for (int i = 0; i < threeShips.length; i ++){
			if (threeShips[i].isSunk == true){
				symbols.add(threeShips[i].symbol);
			}
		}
		for (int i = 0; i < fourShips.length; i ++){
			if (fourShips[i].isSunk == true){
				symbols.add(fourShips[i].symbol);
			}
		}
		for (int i = 0; i < fiveShip.length; i ++){
			if (fiveShip[i].isSunk == true){
				symbols.add(fiveShip[i].symbol);
			}
		}
		return symbols;
	}
	//-------------------------------------------------------------------------------
	void makeDashes(){
		System.out.println("-----------------------");
	}
	//-------------------------------------------------------------------------------
	public static void main(String[] args) {
		new Main();
	}
	//-------------------------------------------------------------------------------
	public Main(){
		boolean hit;
		setUpBoard();

		int choice = menu();
		if (choice == 1){
			setUpGame();
		}
		while (!checkIfWon()){
			displayBoard();
			int[] guess = getCoordinates();
			hit = checkIfHit(guess);
			updateBoardWithGuesses(guess, hit);
		}
		System.out.println("You won! You sunk all the battleships!");
	}
	//-------------------------------------------------------------------------------
	int[] getCoordinates(){
		System.out.println("Input coordinates (A1, J10, etc....):");
		char[] alphabet = "ABCDEFGHIJ".toCharArray();
		Map<Character, Integer> m = new HashMap<Character, Integer>();
		int j = 0;
		for (char c:alphabet){
			m.put(c, j ++);
		}
		String input = scanner.next();
		char letter = input.charAt(0);
		int number = Integer.parseInt((String) input.subSequence(1, input.length()));
		int [] returns = new int[2];
		returns[0] = m.get(letter);
		returns[1] = number - 1;
		return returns;
	}
	//-----------------------------------------------------------------------------
	boolean checkIfHit(int[] guess){
		if (checkSquare(guess[0], guess[1])){
			System.out.println("That's a miss!");
			board[guess[0]][guess[1]] = "O";
			return false;
		}
		else{
			System.out.println("That's a hit!");
			String shipSymbol = board[guess[0]][guess[1]];
			board[guess[0]][guess[1]] = "X";
			if (checkIfSunk(shipSymbol)){
				System.out.println("You sunk a battleship!");
			}
			return true;
		}
	}
	//--------------------------------------------------------------------------------
	boolean checkIfSunk(String shipSymbol){
		boolean sunk = true;
		for (int i = 0; i < boardSize; i ++){
			for (int j = 0; j < boardSize; j ++){
				if (board[i][j] == shipSymbol){
					sunk = false;
				}
			}
		}
		if (sunk == true){
			makeShipSunk(shipSymbol);
		}
		return sunk;
	}
	//--------------------------------------------------------------------------------
	String makeShipSunk(String symbol){
		for (int i = 0; i < twoShips.length; i ++){
			if (symbol.equals(twoShips[i].symbol)){
				twoShips[i].isSunk = true;
				return "end";
			}
		}
		for (int i = 0; i < threeShips.length; i ++){
			if (symbol.equals(threeShips[i].symbol)){
				threeShips[i].isSunk = true;
				return "end";
			}
		}
		for (int i = 0; i < fourShips.length; i ++){
			if (symbol.equals(fourShips[i].symbol)){
				fourShips[i].isSunk = true;
				return "end";
			}
		}
		for (int i = 0; i < fiveShip.length; i ++){
			if (symbol.equals(fiveShip[i].symbol)){
				fiveShip[i].isSunk = true;
				return "end";
			}
		}
		return "end";
	}
	//--------------------------------------------------------------------------------
	boolean checkIfWon(){
		boolean won = true;
		for (int i = 0; i < boardSize; i ++){
			for (int j = 0; j < boardSize; j ++){
				if (board[i][j] != " " && board[i][j] != "X" && board[i][j] != "O"){
					won = false;
				}
			}
		}
		return won;
	}
	//--------------------------------------------------------------------------------
}

class Ships{
	int size;
	Coordinates[] coordinates;
	boolean isSunk = false;
	String symbol;
}
class Coordinates{
	int xCoordinate;
	int yCoordinate;
}
