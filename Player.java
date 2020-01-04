package com.company;

/*
This class is for the Player object
 */

public class Player {
    private String name;
    private char piece;
    private boolean computer; // True for Computer player, false for human player

    // Constructor for new Player Object
    public Player(char piece) {
        this.piece = piece;
        if (piece =='x') {
            this.name = "Player X";
        }
        else if (piece =='o') {
            this.name = "Player O";
        }
    }

    // Method to set the identifier to a computer player
    public void setComputer() {
        this.computer = true;
    }

    // Method to set the identifier to a human player
    public void setHuman() {
        this.computer = false;
    }

    // Method to get the name of this Player
    public String getName() {
        return this.name;
    }

    // Method to get the char symbol of this Player
    public char getPiece() {
        return this.piece;
    }

    // Method to return the identifier as computer
    public boolean isComputer() {
        return this.computer;
    }

    // Method to return the identifier as human
    public boolean isHuman() {
        return !this.computer;
    }
}
