package sample;

import java.util.ArrayList;
import java.util.List;

public class Round {

    Tile tile = new Tile();
    public int moveX = 0;
    public int moveO = 0;
    public int move = moveO + moveX;
    public Tile[] tiles;
    public boolean turnXOrY = true;
    public boolean playable = true;
    List<Round> rounds = new ArrayList<Round>();

    public Round(Tile... tiles) {
        this.tiles = tiles;
    }

//    public void checkWinner() {
//        for (Round round : rounds) {
//            if(round.isWon()) {
//                playable = false;
//                tile.playCongratsAnimation(round);
//                break;
//            } playable = true;
//        }
//
//    }

    public boolean isWon() {
        if(tiles[0].getValue().isEmpty() || tiles[1].getValue().isEmpty() || tiles[2].getValue().isEmpty())
            return false;

        return tiles[0].getValue().equals(tiles[1].getValue()) &&
                tiles[0].getValue().equals(tiles[2].getValue()) &&
                tiles[1].getValue().equals(tiles[2].getValue());

    }

    public String getWinnerSymbol(){
        String theWinner = "DRAW";
        if (isWon() || move >= 9) {
            theWinner = tiles[0].getValue();

        }
        return theWinner;
    }

}
