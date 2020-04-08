//package sample;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Round {
//
//    private final Tile[] tiles;
//    GameDefinition gameDefinition;
//
//    public Round(GameDefinition gameDefinition, Tile... tiles) {
//        this.gameDefinition = gameDefinition;
//        this.tiles = tiles;
//    }
//
//    public void combos() {
//        for(int y = 0; y < 3; y++) {
//            gameDefinition.getRounds().add(new Main.Round(gameDefinition.getBoard()[0][y], gameDefinition.getBoard()[1][y], gameDefinition.getBoard()[2][y]));
//        }
//        for(int x = 0; x < 3; x++) {
//            gameDefinition.getRounds().add(new Main.Round(gameDefinition.getBoard()[x][0], gameDefinition.getBoard()[x][1], gameDefinition.getBoard()[x][2]));
//        }
//        gameDefinition.getRounds().add(new Main.Round(gameDefinition.getBoard()[0][0], gameDefinition.getBoard()[1][1], gameDefinition.getBoard()[2][2]));
//        gameDefinition.getRounds().add(new Main.Round(gameDefinition.getBoard()[0][2], gameDefinition.getBoard()[1][1], gameDefinition.getBoard()[2][0]));
//    }
//
//}
