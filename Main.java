import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	private static int[][] moveTable = {{2, -1},{1, 2},{-1, 2},{-2, 1},{-2,-1},{-1,-2},{1,-2},{2, -1}};
	private static Scanner in = new Scanner(System.in);
	private static Board gameBoard;
	private static int boardSize;
	private static int moveNum;
	
	public static void main(String[] args)
	{
		moveNum = 0;
		System.out.println("How big is the board? (5 for 5x5 etc.): ");
		boardSize = in.nextInt();
		gameBoard = new Board(boardSize);
		knightsTour();
	}
	
	public static boolean knightsTour()
	{
		System.out.println("What is the starting X-Coordinate?: ");
		int startX = in.nextInt();
		System.out.println("What is the starting Y-Coordinate?: ");
		int startY = in.nextInt();
		if(!knightsTourRec(startX, startY, 1))
		{
			System.out.println("Error. Does not compute.");
			return false;
		}
		else
		{
			System.out.println("Success! It took " + moveNum + " moves to complete!");
		}
		in.close();
		return true;
	}
	public static boolean knightsTourRec(int x, int y, int iter)
	{
		int nextX, nextY;
		if(iter == (boardSize * boardSize))
		{
			return true;
		}
		for(int i = 0; i < 8; i++)
		{
			nextX = x + moveTable[i][0];
			nextY = y + moveTable[i][1];
			if(gameBoard.isMove(nextX, nextY))
			{
				gameBoard.get(nextX, nextY).visited = true;
				if(knightsTourRec(nextX, nextY, iter + 1))
				{
					moveNum++;
					
					return true;
				}
				else
				{
					gameBoard.get(nextX, nextY).visited = false;
				}
			}
		}
		return false;
	}
}

class Board {
	Random rand = new Random();
	Node[][] gameBoard;
	Board(int x)
	{
		gameBoard = new Node[x][x];
		for(int j = 0; j < x; j++)
		{
			for(int i = 0; i < x; i++)
			{
				gameBoard[j][i] = new Node();
			}
		}
	}
	void print()
	{
		for(int j = 0; j < gameBoard.length; j++)
		{
			for(int i = 0; i < gameBoard.length; i++)
			{
				System.out.print(gameBoard[j][i].visited + "|");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------");
	}
	
	boolean isMove(int x, int y)
	{
		boolean move = false;
		if(x >= 0 && x < gameBoard.length){
			if(y >= 0 && y < gameBoard.length){
				if(gameBoard[x][y].visited == false){
					move = true;
				}
			}
		}
		else{
			move = false;
		}
		return move;
	}
	
	Node get(int x, int y)
	{
		return gameBoard[x][y];
	}
}
	
class Node {
	boolean visited;
	Node()
	{
		visited = false;
	}
	void tick()
	{
		visited = true;
	}
	boolean visited()
	{
		return visited;
	}
}
