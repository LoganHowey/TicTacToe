package util;

import javax.swing.*;

public class Cell {

    private int row;
    private int col;
    private Seed content;
    private char[][] board;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new char[][]{
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        for (char[] rows : board) {
            for (char cols : rows) {
                System.out.print(cols);
            }
            System.out.println();
        }
    }

    public void renderBoard(String playerInput, Seed currentPlayer) {
        char replacer = ' ';
        if (currentPlayer == Seed.X) {
            replacer = 'X';
        } else if (currentPlayer == Seed.O) {
            replacer = 'O';
        }
        switch (playerInput) {
            case "a1":
                board[0][0] = replacer;
                break;
            case "a2":
                board[0][2] = replacer;
                break;
            case "a3":
                board[0][4] = replacer;
                break;
            case "b1":
                board[2][0] = replacer;
                break;
            case "b2":
                board[2][2] = replacer;
                break;
            case "b3":
                board[2][4] = replacer;
                break;
            case "c1":
                board[4][0] = replacer;
                break;
            case "c2":
                board[4][2] = replacer;
                break;
            case "c3":
                board[4][4] = replacer;
                break;
            default:
                System.out.println("oops");
        }
        for (char[] rows : board) {
            for (char cols : rows) {
                System.out.print(cols);
            }
            System.out.println();
        }
    }

    // a1, a2, a3 | b1, b2, b3 | c1, c2, c3 | a1,b1,c1 | a2,b2,b3 | a3,b3,c3| a1,b2,c3 | a3,b2,c1

}