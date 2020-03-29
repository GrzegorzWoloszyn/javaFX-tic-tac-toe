package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import sample.resource.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    public Pane root = new Pane();
    public boolean turnXOrY = true;
    public boolean playable = true;
    public Tile[][] board = new Tile[3][3];
    public List<Round> rounds = new ArrayList<>();
    public MenuBar menu = new MenuBar();
    public int moveX = 0;
    public int moveO = 0;
    public static int xPoints = 0;
    public static int oPoints = 0;
    private static final Random RANDOM = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("TIC-TAC-TOE");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void close() {
        Platform.exit();
        System.exit(0);
    }

    public void newGame() {
        try {
            createContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Parent createContent() throws Exception {
        root.setPrefSize(608, 608);
        root.setLayoutY(30);
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.setTranslateX(col * 198);
                tile.setTranslateY(row * 198);
                root.getChildren().add(tile);
                board[col][row] = tile;
                board[col][row].setOwnerPlayer(Owner.EMPTY);
            }
        }


        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        Menu menuGame = new Menu("New Game");
        menuFile.getItems().add(exit);
        menu.getMenus().addAll(menuFile, menuGame);
        exit.setOnAction(actionEvent -> Main.close());
        menuGame.setOnAction(actionEvent -> newGame());
        root.getChildren().add(menu);

        //horizontal
        for(int y = 0; y < 3; y++) {
            rounds.add(new Round(board[0][y], board[1][y], board[2][y]));
        }
        //vertical
        for(int x = 0; x < 3; x++) {
            rounds.add(new Round(board[x][0], board[x][1], board[x][2]));
        }
        //diagonals
        rounds.add(new Round(board[0][0], board[1][1], board[2][2]));
        rounds.add(new Round(board[0][2], board[1][1], board[2][0]));

        return root;
    }

    public void checkWinner() {
        for (Round round : rounds) {
            if(round.isWon()  || (moveX + moveO) == 9) {
                playable = false;
                playCongratsAnimation(round);
                break;
            }
            playable = true;
        }
    }

    public void playCongratsAnimation(Round round) {
        Text winText = new Text("The winner is: " + round.getWinnerSymbol() + " , congratulations! Rematch?");
        winText.setFont(new Font(25));
        winText.setFill(Color.DARKGOLDENROD);
        Text scoreX = new Text("X points: " + statsX(round));
        Text scoreO = new Text("O points: " + statsO(round));
        scoreX.setFont(Font.font(25));
        scoreO.setFont(Font.font(25));
        scoreX.setFill(Color.BLACK);
        scoreO.setFill(Color.BLACK);
        scoreX.setLayoutY(570);
        scoreO.setLayoutY(550);
        scoreO.setLayoutX(10);
        scoreX.setLayoutX(10);
        root.getChildren().addAll(winText, scoreO, scoreX);

        if (round.isWon()) {
            Line line = new Line();
            line.setStartX(round.tiles[0].getCentreX());
            line.setStartY(round.tiles[0].getCentreY());
            line.setEndX(round.tiles[0].getCentreX());
            line.setEndY(round.tiles[0].getCentreY());

            root.getChildren().add(line);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                    new KeyValue(line.endXProperty(), round.tiles[2].getCentreX()),
                    new KeyValue(line.endYProperty(), round.tiles[2].getCentreY())));

            timeline.play();
        }
    }

    public class Round {

        public Tile[] tiles;
        public Round(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isWon() {
            if(tiles[0].getValue().isEmpty())
                return false;

            return tiles[0].getValue().equals(tiles[1].getValue()) &&
                    tiles[0].getValue().equals(tiles[2].getValue());
        }

        public String getWinnerSymbol(){
            String theWinner = "DRAW";
            if (isWon() || (moveX + moveO) == 9) {
                theWinner =  tiles[0].getValue();
            }
            return theWinner;
        }

    }

    public int statsX(Round round) {
        if(round.getWinnerSymbol().equals("X"))
            xPoints ++;
            return xPoints;
    }

    public int statsO(Round round) {
        if(round.getWinnerSymbol().equals("O"))
            oPoints++;
        return oPoints;
    }

    public class Tile extends StackPane implements EventHandler<ActionEvent> {
        public Text text = new Text();
        private Owner ownerPlayer = Owner.EMPTY;

        public Tile() throws Exception {
            Rectangle chart = new Rectangle(200, 200, Color.GREEN);
            chart.setStroke(Color.WHITE);
            chart.setStrokeWidth(10);
            setAlignment(Pos.CENTER);

            text.setFont(new Font(80));
            text.setFill(Color.YELLOW);
            getChildren().addAll(chart, text);

            makeMove();
            //cpuMove(board);

//            setOnMouseClicked(mouseEvent -> {
//                if(!playable)
//                    return;
//                        if (mouseEvent.getButton() == MouseButton.PRIMARY && !(getValue().equals("O"))) {
//                                if (!turnXOrY) {
//                                    return;
//                                }
//                                else {
//                                    moveX++;
//                                    drawX();
//                                    turnXOrY = false;
//                                    checkWinner();
//                                }
//                        }
//                        else if (mouseEvent.getButton() == MouseButton.SECONDARY && !(getValue().equals("X"))) {
//                            if (turnXOrY)
//                                return;
//                            else {
//                                moveO++;
//                                drawO();
//                                turnXOrY = true;
//                                checkWinner();
//                            }
//                        }
//                });
        }

        public void makeMove() {
           setOnMouseClicked(mouseEvent -> {
                if(!playable)
                    return;
                        if (mouseEvent.getButton() == MouseButton.PRIMARY && !(getValue().equals("O"))) {
                                if (!turnXOrY) {
                                    return;
                                }
                                else {
                                    moveX++;
                                    drawX();
                                    turnXOrY = false;
                                    checkWinner();
                                }
                        }
                        else if (mouseEvent.getButton() == MouseButton.SECONDARY && !(getValue().equals("X"))) {
                            if (turnXOrY)
                                return;
                            else {
                                moveO++;
                                drawO();
                                turnXOrY = true;
                                checkWinner();
                            }
                        }
                });
    }


        public void cpuMove(Tile[][] tiles) throws Exception {
            List <Tile> remainedTiles = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(tiles[i][j].getOwnerPlayer().equals(Owner.EMPTY) && tiles[i][j] != null ) {
                        remainedTiles.add(tiles[i][j]);
                    };
                }
            }
            if (remainedTiles.size() > 0) {
                int a = RANDOM.nextInt(remainedTiles.size());
                Tile chosenTile = remainedTiles.get(a);
                chosenTile.drawO();
                chosenTile.setOwnerPlayer(Owner.O);
            }
        }

        public void drawX() {
            text.setText("X");
        }

        public void drawO() {

            text.setText("O");
        }

        public String getValue() {
            return text.getText();
        }

        public double getCentreX() {
            return getTranslateX() + 108;
        }

        public double getCentreY() {
            return getTranslateY() + 108;
        }

        public Owner getOwnerPlayer() {
            return ownerPlayer;
        }

        public void setOwnerPlayer(Owner ownerPlayer) {
            this.ownerPlayer = ownerPlayer;
        }

        @Override
        public void handle(ActionEvent actionEvent) {

            if (actionEvent.getSource() == MouseButton.PRIMARY && !(getValue().equals("O")))
                actionEvent.getSource();
            moveX++;
            drawX();
            turnXOrY = false;
            checkWinner();

        }
    }



}
