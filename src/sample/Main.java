package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    public Pane root = new Pane();
    Tile tile = new Tile();
    StartGame startGame = new StartGame();
    //public boolean turnXOrY = true;
    public boolean playable = true;
    //public Tile[][] board = new Tile[3][3];
    public List<Round> rounds = new ArrayList<>();
    public static MenuBar menu = new MenuBar();
    public int moveX = 0;
    public int moveO = 0;
    public int move = moveO + moveX;

//    public Parent createContent() {
//        root.setPrefSize(608, 608);
//        root.setLayoutY(30);
//        for(int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                Tile tile = new Tile();
//                tile.setTranslateX(col * 198);
//                tile.setTranslateY(row * 198);
//                root.getChildren().add(tile);
//                board[col][row] = tile;
//            }
//        }
//
//        for(int y = 0; y < 3; y++) {
//            rounds.add(new Round(board[0][y], board[1][y], board[2][y]));
//        }
//
//        for(int x = 0; x < 3; x++) {
//            rounds.add(new Round(board[x][0], board[x][1], board[x][2]));
//        }
//        rounds.add(new Round(board[0][0], board[1][1], board[2][2]));
//        rounds.add(new Round(board[2][0], board[1][1], board[0][2]));
//        return root;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(startGame.createContent()));
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        Menu menuGame = new Menu("New Game");
        //menuGame.setOnAction(actionEvent -> newGame());
        exit.setOnAction(actionEvent -> Main.close());
        menuFile.getItems().add(exit);
        menu.getMenus().addAll(menuFile, menuGame);
        root.getChildren().add(menu);
       }

       public static void close() {
           Platform.exit();
           System.exit(0);
       }


    public void checkWinner() {
        for (Round round : rounds) {
            if(round.isWon() || move >= 9) {
                playable = false;
                tile.playCongratsAnimation(round);
                break;
            } playable = true;
        }

    }

//    public void playCongratsAnimation(Round round) {
//
//        Text winText = new Text("The winner is: " + round.getWinnerSymbol() + " , congratulations! Rematch?" );
//        winText.setFont(new Font(25));
//        winText.setFill(Color.DARKGOLDENROD);
//
//        if (round.isWon()) {
//            Line line = new Line();
//            line.setStartX(round.tiles[0].getCentreX());
//            line.setStartY(round.tiles[0].getCentreY());
//            line.setEndX(round.tiles[0].getCentreX());
//            line.setEndY(round.tiles[0].getCentreY());
//
//            root.getChildren().add(line);
//
//            Timeline timeline = new Timeline();
//            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
//                    new KeyValue(line.endXProperty(), round.tiles[2].getCentreX()),
//                    new KeyValue(line.endYProperty(), round.tiles[2].getCentreY())));
//
//            timeline.play();
//        } else{}
//            root.getChildren().add(winText);
//
//    }

//    public class Round {
//
//        public Tile[] tiles;
//        public Round(Tile... tiles) {
//            this.tiles = tiles;
//        }
//
//        public boolean isWon() {
//            if(tiles[0].getValue().isEmpty() || tiles[1].getValue().isEmpty() || tiles[2].getValue().isEmpty())
//                return false;
//
//            return tiles[0].getValue().equals(tiles[1].getValue()) &&
//                    tiles[0].getValue().equals(tiles[2].getValue()) &&
//                    tiles[1].getValue().equals(tiles[2].getValue());
//
//        }
//
//        public String getWinnerSymbol(){
//            String theWinner = "DRAW";
//            if (isWon() || move >= 9) {
//                theWinner = tiles[0].getValue();
//
//            }
//            return theWinner;
//        }
//
//    }

//    public class Tile extends StackPane {
//        public Text text = new Text();
////        Image imageX = new Image(getClass().getResourceAsStream("X.PNG"));
////        Image imageY = new Image(getClass().getResourceAsStream("Y.PNG"));
//
//        public Tile() {
//            Rectangle chart = new Rectangle(200, 200, Color.GREEN);
//            chart.setStroke(Color.WHITE);
//            chart.setStrokeWidth(10);
//
//            setAlignment(Pos.CENTER);
//            getChildren().addAll(chart);
//
//            setOnMouseClicked(mouseEvent -> {
//                if(!playable)
//                    return;
//                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
//                                if (!turnXOrY) {
//                                    return;
//                                }else if(text.getText() == "O"){
//                                    return;
//                                }
//                                else {
//                                    drawX();
//                                    moveX++;
//                                    turnXOrY = false;
//                                    checkWinner();
//                                }
//
//                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
//                            if (turnXOrY) {
//                                return;
//                            } else if(text.getText() == "X"){
//                                return;
//                            }
//                            else {
//                                drawO();
//                                moveO++;
//                            turnXOrY = true;
//                            checkWinner();
//                            }
//                        }
//
//                    text.setFont(new Font(80));
//                    text.setFill(Color.YELLOW);
//                    getChildren().add(text);
//                });
//
//        }
//
//        public void drawX() {
//            text.setText("X");
//            setAlignment(Pos.CENTER);
//        }
//
//        public void drawO() {
//            setAlignment(Pos.CENTER);
//            text.setText("O");
//        }
//
//        public String getValue() {
//            return text.getText();
//        }
//
//        public double getCentreX() {
//            return getTranslateX() + 108;
//        }
//
//        public double getCentreY() {
//            return getTranslateY() + 108;
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
