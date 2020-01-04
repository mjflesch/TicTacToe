package com.company;
import java.util.*;

import static com.company.Main.getValidInt;


/*
This class is for the Board object which tracks the current state of the game
The data of the board is stored in a char array
'x' indicates Player X
'o' indicates Player O
'-' indicates empty space on the board
 */
public class Board {
    // Game board is stored as a char array
    private char[][] board;
    private int size;
    private int gameMode; // 1 for Human vs. Human, 2 for Human vs. Computer
    private boolean turn; // True for playerX turn, False for PlayerO turn

    /*
    Constructor for a new Tic-Tac-Toe Board
    Board object represents the Tic-Tac-Toe grid as an nxn char array
     */
    public Board(int size, int mode) {
        this.size = size;
        this.board = new char[size][size];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                board[i][j]= '-';
            }
        }
        this.gameMode = mode;
        this.turn = true;
    }

    /*
    Print Tic-Tac-Toe Board to the console
    */
    public void displayBoard() {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // Method to return game mode
    public int getGameMode() {
        return this.gameMode;
    }

    // Method to modify the char array in the game state corresponding to move
    public void modifyBoard(Player curr, int row, int col){
        this.board[row][col] = curr.getPiece();
        this.turn = !this.turn; // Invert the turn counter
    }

    // Method to identify turn: Player X is true, Player O is false
    public boolean isTurn() {
        return this.turn;
    }

    // Method to check the board for any open space on the board
    public boolean movesRemain() {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j] == '-') {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Method to calculate if the move passed in results in a win
    This operates under the assumption that the I only need to check
    the row, col, and diagonals for the move passed in because any
    previous moves would have been checked for a winner at that time.
    int[] move is in the form [row][col]
     */
    public boolean winningMove(int[] move) {
        // Check Row of the move for winner
        int row = move[0];
        char crow = this.board[row][0];
        boolean rowWinner = true;
        for (int i=1; i<this.board[row].length; i++) {
            if (crow != this.board[row][i]) {
                rowWinner=false;
                break;
            }
        }
        if (rowWinner) {
            return true;
        }

        // Check Column of the move for winner
        int col = move[1];
        char ccol = this.board[0][col];
        boolean colWinner = true;
        for (int i=1; i<this.board.length; i++) {
            if (ccol != this.board[i][col]) {
                colWinner = false;
                break;
            }
        }
        if (colWinner) {
            return true;
        }

        // Check Diagonal of the move for winner
        // Identify being on the diagonal by row == col
        if (row == col) {
            char dcol = this.board[0][0];
            boolean diagWinner = true;
            for (int i = 1; i < this.board.length; i++) {
                if (dcol != this.board[i][i]) {
                    diagWinner = false;
                    break;
                }
            }
            if (diagWinner) {
                return true;
            }
        }

        // Check Reverse Diagonal for winner
        // Identify being on the reverse diagonal by row+col == size of board
        if (row+col == board.length-1) {
            char rdcol = this.board[0][board.length-1];
            boolean rdiagWinner = true;
            outer: for (int i=1; i<board.length; i++) {
                for (int j=0; j<board[i].length; j++) {
                    if (i+j == board.length-1) {
                        if (rdcol != this.board[i][j]) {
                            rdiagWinner = false;
                            break outer;
                        }
                    }
                }
            }
            if (rdiagWinner) {
                return true;
            }
        }

        return false;
    }

    // Method to check of the move is a valid empty location in the array
    private boolean validMove(int r, int c) {
        // Check if position r,c is empty
        if (board[r][c]=='-') {
            return true;
        }
        else {
            return false;
        }
    }

    // Method to get user input or AI input for move
    public int[] getMove(Player player, Scanner scan) {
        int[] move = new int[2];
        if (player.isComputer()) {
            //Implement AI get Move Algorithm
        }
        else if (player.isHuman()) {
            do {
                int row = getValidInt(scan,1,this.size,"Please enter row: ");
                int col = getValidInt(scan,1,this.size,"Please enter col: ");
                move[0]=row-1;
                move[1]=col-1;
                if (!validMove(move[0],move[1])) {
                    System.out.println("Invalid Move Location");
                    System.out.println(player.getName());
                    displayBoard();
                }
            } while (!validMove(move[0],move[1]));
        }
        return move;
    }
}
