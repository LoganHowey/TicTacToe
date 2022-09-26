package util;

import javax.naming.PartialResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Cell board;
    private State currentState;
    private Seed currentPlayer;
    private int turn;

    private List<String> playerX;

    private List<String> playerO;

    private String[] available;

    public Game() {
        this.board = new Cell(3, 3);
        this.turn = 0;
        this.currentState = State.PLAYING;
        this.playerX = new ArrayList<String>();
        this.playerO = new ArrayList<String>();
        this.available = new String[]{"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c1,", "c2", "c3"};
    }

    public void initiateGame(Game newGame) {
        while (this.currentState == State.PLAYING) {
            newGame.gameTurn();
        }
        if (currentState == State.GAME_TIED) {
            System.out.println("Cat's Game");
        } else if (currentState == State.X_WON) {
            System.out.println("X Won");
        } else if (currentState == State.O_WON) {
            System.out.println("O Won");
        } else {
            System.out.println("IDK what happened.");
        }
        Scanner playAgain = new Scanner(System.in);
        System.out.println("Play Again?");
        String againPlay = playAgain.nextLine();
        if (againPlay.equals("yes")){
           Game anotherGame = newGameMaker();
           initiateGame(anotherGame);
        }
        else {
            return;
        }
    }

    public Game newGameMaker(){
        Game anotherGame = new Game();
        return anotherGame;
    }

    public static boolean check (String[]arr, String val){
            boolean b = false;

            for (String i : arr) {
                if (i.equals(val)) {
                    b = true;
                    break;
                }
            }
            return b;
        }

        public void newGame () {
        }

        public void gameTurn () {
            if (currentState == State.PLAYING) {
                boolean checking = true;
                String input = null;
                if (turn == 0) {
                    currentPlayer = Seed.X;
                    turn++;
                } else if (turn % 2 == 0) {
                    currentPlayer = Seed.X;
                    turn++;
                } else if (turn % 2 == 1) {
                    currentPlayer = Seed.O;
                    turn++;
                }
                if (currentPlayer == Seed.X) {
                    while (checking) {
                        System.out.println("X's turn");
                        Scanner playerInput = new Scanner(System.in);
                        System.out.println("Choose Cell");
                        System.out.println(Arrays.toString(available));
                        input = playerInput.nextLine();
                        if (check(available, input)) {
                            checking = false;
                        } else System.out.println("Not a valid cell");
                    }
                    renderBoard(input, currentPlayer);
                    playerX.add(input);
                    available[Arrays.asList(available).indexOf(input)] = "Used";
                    checkWin(playerX);

                }
                if (currentPlayer == Seed.O) {
                    while (checking) {
                        System.out.println("Y's turn");
                        Scanner playerInput = new Scanner(System.in);
                        System.out.println("Choose Cell");
                        System.out.println(Arrays.toString(available));
                        input = playerInput.nextLine();
                        if (check(available, input)) {
                            checking = false;
                        } else System.out.println("Not a valid cell");
                    }
                    renderBoard(input, currentPlayer);
                    playerO.add(input);
                    available[Arrays.asList(available).indexOf(input)] = "Used";
                    checkWin(playerO);
                }
            }
        }

        public void renderBoard (String playerInput, Seed currentPlayer){
            board.renderBoard(playerInput, currentPlayer);
        }

        public void badInput () {
            turn--;
            gameTurn();
        }

        public void checkWin (List < String > playerPos) {
            List<String> topRow = List.of("a1", "a2", "a3");
            List<String> midRow = List.of("b1", "b2", "b3");
            List<String> bottomRow = List.of("c1", "c2", "c3");
            List<String> topCol = List.of("a1", "b1", "c1");
            List<String> midCol = List.of("a2", "a2", "c2");
            List<String> bottomCol = List.of("a3", "b3", "c3");
            List<String> leftDiag = List.of("a1", "b2", "c3");
            List<String> rightDiag = List.of("a3", "b2", "c1");

            List<List> winners = new ArrayList<List>();
            winners.add(topRow);
            winners.add(topCol);
            winners.add(midCol);
            winners.add(midRow);
            winners.add(bottomCol);
            winners.add(bottomRow);
            winners.add(leftDiag);
            winners.add(rightDiag);

            for (List winner : winners) {
                if (playerPos.containsAll(winner)) {
                    if (turn % 2 == 0) {
                        currentState = State.O_WON;
                    } else {
                        currentState = State.X_WON;
                    }
                    return;

                }
            }
            if (turn > 8) {
                currentState = State.GAME_TIED;
                return;
            }
            currentState = State.PLAYING;
        }

        public static void main (String[]args){
            Game newGame = new Game();
            newGame.initiateGame(newGame);
        }
    }

// a1, a2, a3 | b1, b2, b3 | c1, c2, c3 | a1,b1,c1 | a2,b2,b3 | a3,b3,c3| a1,b2,c3 | a3,b2,c1
