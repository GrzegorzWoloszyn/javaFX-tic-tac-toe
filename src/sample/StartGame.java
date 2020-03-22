//package sample;
//
//import javafx.application.Platform;
//import javafx.scene.Parent;
//import javafx.scene.control.MenuBar;
//import javafx.scene.layout.Pane;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StartGame {
//
//    Pane root = new Pane();
//    Tile[][] board = new Tile[3][3];
//    List<Round> rounds = new ArrayList<Round>();
//    public static MenuBar menu = new MenuBar();
//    int moveX = 0;
//    int moveO = 0;
//    int move = moveO + moveX;
//
//        public Parent createContent() {
//            root.setPrefSize(608, 608);
//            root.setLayoutY(30);
//
//            for(int row = 0; row < 3; row++) {
//                for (int col = 0; col < 3; col++) {
//                    Tile tile = new Tile();
//                    tile.setTranslateX(col * 198);
//                    tile.setTranslateY(row * 198);
//
//                    root.getChildren().add(tile);
//
//                    board[col][row] = tile;
//                }
//            }
//
//            for(int y = 0; y < 3; y++) {
//                rounds.add(new Round(board[0][y], board[1][y], board[2][y]));
//            }
//
//            for(int x = 0; x < 3; x++) {
//                rounds.add(new Round(board[x][0], board[x][1], board[x][2]));
//            }
//
//            rounds.add(new Round(board[0][0], board[1][1], board[2][2]));
//            rounds.add(new Round(board[2][0], board[1][1], board[0][2]));
//
//            return root;
//
//        }
//
//    public static void close() {
//        Platform.exit();
//        System.exit(0);
//    }
//
//}
//
//
