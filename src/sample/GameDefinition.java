package sample;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDefinition {
    public boolean playerXMovement = true;
    public boolean gamePlayable = true;
    public Main.Tile[][] board = new Main.Tile[3][3];
    public List<Main.Round> rounds = new ArrayList<>();
    public MenuBar menu = new MenuBar();
    public int numberOfXMoves = 0;
    public int numberOfOMoves = 0;
    public int xPlayerPoints = 0;
    public int oPlayerPoints = 0;
    private static final Random RANDOM = new Random();
    Main.Tile[][] tiles;

    public Main.Tile[][] getTiles() {
        return tiles;
    }

    public boolean isPlayerXMovement() {
        return playerXMovement;
    }

    public void setPlayerXMovement(boolean playerXMovement) {
        this.playerXMovement = playerXMovement;
    }

    public boolean isGamePlayable() {
        return gamePlayable;
    }

    public void setGamePlayable(boolean gamePlayable) {
        this.gamePlayable = gamePlayable;
    }

    public Main.Tile[][] getBoard() {
        return board;
    }

    public List<Main.Round> getRounds() {
        return rounds;
    }

    public MenuBar getMenu() {
        return menu;
    }

    public int getNumberOfXMoves() {
        return numberOfXMoves;
    }

    public void setNumberOfXMoves(int numberOfXMoves) {
        this.numberOfXMoves = numberOfXMoves;
    }

    public int getNumberOfOMoves() {
        return numberOfOMoves;
    }

    public int getxPlayerPoints() {
        return xPlayerPoints;
    }

    public int getoPlayerPoints() {
        return oPlayerPoints;
    }

    public static Random getRANDOM() {
        return RANDOM;
    }
}
