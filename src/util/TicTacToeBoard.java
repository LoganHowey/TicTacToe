package util;

public class TicTacToeBoard {

    public int ROWS = 3;
    public int COLS = 3;
    private Cell cells = new Cell(ROWS, COLS);

    public TicTacToeBoard(){

    }

    public void initiateGame(){

    }

    public void newGame(){

    }

    public State gameTurn(Seed player, int row, int col){
        return State.PLAYING;
    }

    public void renderBoard(){

    }


}
