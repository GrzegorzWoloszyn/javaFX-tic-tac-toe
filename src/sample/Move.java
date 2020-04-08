//package sample;
//
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.StackPane;
//import javafx.scene.text.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Move extends StackPane {
//
//    Symbol symbol = new Symbol();
//    final Random RANDOM = new Random();
//    Text text;
//
//    public void makeMove(GameDefinition gameDefinition) {
//        if(!gameDefinition.isGamePlayable())
//            return;
//        setOnMouseClicked(mouseEvent -> {
//            if (mouseEvent.getButton() == MouseButton.PRIMARY && !(symbol.getSymbol().equals("O")) && !(symbol.getSymbol().equals("X"))) {
//                if (!gameDefinition.isPlayerXMovement()) {
//                    return;
//                }
//                else {
//                    playerMove();
//                }
//            }
//            if(!gameDefinition.isGamePlayable());
//                return;
//            cpuMove(gameDefinition);
//        });
//    }
//
//    public void playerMove(int numberOfXMoves, boolean playerXMovement) {
//        numberOfXMoves++;
//        symbol.drawSymbol("X");
//        playerXMovement = false;
//        checkWinner();
//        text.setText("X");
//    }
//
//    public void cpuMove(GameDefinition gameDefinition){
//        if(gameDefinition.isPlayerXMovement())
//            return;
//        List<Main.Tile> remainedTiles = new ArrayList<>();
//        for(int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if(gameDefinition.getTiles()[i][j] != null && gameDefinition.getTiles()[i][j].getValue().isEmpty()) {
//                    remainedTiles.add(gameDefinition.getTiles()[i][j]);
//                }
//            }
//        }
//        if (remainedTiles.size() > 0) {
//            int a = RANDOM.nextInt(remainedTiles.size());
//            Main.Tile chosenTile = remainedTiles.get(a);
//            symbol.drawSymbol("O");
//            chosenTile.text.setText("O");
//            gameDefinition.setNumberOfXMoves(1);
//            gameDefinition.setPlayerXMovement(true);
//            checkWinner();
//        }
//    }
//
//}
