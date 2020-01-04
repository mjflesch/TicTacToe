package com.company;

import java.util.*;

public class Main {

    /*
    Main Method - This is the game play driver for a simple Tic-Tac-Toe game.
    The game can be played on the standard 3x3 board or expanded up to 9x9.
    The game can be played between 2 people or 1 player against the computer.
     */
    public static void main(String[] args) {
        // Print Gameplay Welcome
        System.out.println("Welcome to Tic-Tac-Toe");
        System.out.println("1- Human vs. Human");
        System.out.println("2- Human vs. Computer");
        Scanner scan = new Scanner(System.in);

        // Select Gameplay Mode
        int mode = getValidInt(scan,1,2,"Please enter gameplay mode: ");
        if (mode==1) {
            System.out.println("Human vs. Human Game");
        }
        else if (mode==2) {
            System.out.println("Sorry, Computer AI has not been implemented yet.");
            System.exit(0);
        }

        // Create Board Object and Display
        int num = getValidInt(scan, 3, 9, "Please enter board size: ");
        Board board = new Board(num,mode);
        board.displayBoard();

        // Create 2 Players Objects Player X is always Human and always goes first
        Player playerX = new Player('x');
        playerX.setHuman();
        Player playerO = new Player('o');
        // Player O may be human or computer depending on game mode selection
        if (board.getGameMode()==2) {
            playerO.setComputer();
        }

        // Game play Loop
        while (board.movesRemain()) {
            int[] move;
            // Player X Turn
            if (board.isTurn()) {
                System.out.println(playerX.getName());
                // Get Move from Player X
                move = board.getMove(playerX,scan);
                board.modifyBoard(playerX,move[0],move[1]);
                // Check if the move resulted in a win
                if (board.winningMove(move)) {
                    board.displayBoard();
                    System.out.println(playerX.getName() + " wins!");
                    break;
                }
            }
            // Player O Turn
            else if (!board.isTurn()) {
                System.out.println(playerO.getName());
                // Get move from Player O
                move = board.getMove(playerO,scan);
                board.modifyBoard(playerO,move[0],move[1]);
                // Check if the move resulted in a win
                if (board.winningMove(move)) {
                    board.displayBoard();
                    System.out.println(playerO.getName() + " wins!");
                    break;
                }
            }
            board.displayBoard();
        }
        // Print Exit Message and close the program
        System.out.println("Game Over!");
    }

    /*
    Helper method to get user input and validate
     */
    public static int getValidInt(Scanner scan, int minVal, int maxVal, String message) {
        System.out.print(message);
        int num = scan.nextInt();
        System.out.println();
        if (num<minVal || num>maxVal) {
            System.out.println("Invalid Input - Enter an integer between " + minVal + " and " + maxVal);
            return getValidInt(scan,minVal,maxVal,message);
        }
        return num;
    }
}
