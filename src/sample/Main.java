package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    public Pane root = new Pane();
    public boolean playerXMovement = true;
    public boolean gamePlayable = true;
    public Tile[][] board = new Tile[3][3];
    public List<Round> rounds = new ArrayList<>();
    public MenuBar menu = new MenuBar();
    public int numberOfXMoves = 0;
    public int numberOfOMoves = 0;
    public int xPlayerPoints = 0;
    public int oPlayerPoints = 0;
    private static final Random RANDOM = new Random();

    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    void startGame(Stage stage) {
        Scene scene = new Scene(createGameBoard());
        stage.setScene(scene);
        createMenu(stage);
        combos();
        stage.setTitle("TIC-TAC-TOE");
        stage.show();
    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void restartGame(Tile[][] board, Stage stage) {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                root.getChildren().clear();
                board[i][j].getChildren().clear();
                board[i][j].text.setText(" ");
            }
        }
        rounds = new ArrayList<>();
        gamePlayable = true;
        playerXMovement = true;
        stage.setScene(new Scene(createGameBoard()));
        createMenu(stage);
        combos();
        stage.setTitle("TIC-TAC-TOE");
        stage.show();

    }

    public Pane createGameBoard() {
        root.setPrefSize(608, 608);
        root.setLayoutY(30);
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.setTranslateX(col * 198);
                tile.setTranslateY(row * 198);
                root.getChildren().add(tile);
                board[col][row] = tile;
            }
        }
        return new Pane(root);
    }

    public void combos() {
        for(int y = 0; y < 3; y++) {
            rounds.add(new Round(board[0][y], board[1][y], board[2][y]));
        }
        for(int x = 0; x < 3; x++) {
            rounds.add(new Round(board[x][0], board[x][1], board[x][2]));
        }
        rounds.add(new Round(board[0][0], board[1][1], board[2][2]));
        rounds.add(new Round(board[0][2], board[1][1], board[2][0]));
    }

    public void createMenu(Stage stage) {
        Menu menuFile = new Menu("File");
        Menu menuGame = new Menu("New Game");
        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().addAll(exit, menuGame);
        menu.getMenus().add(menuFile);
        exit.setOnAction(actionEvent -> Main.exit());
        menuGame.setOnAction(actionEvent -> {
            restartGame(board, stage);
        });

        root.getChildren().add(menu);
    }

    public void checkWinner() {
        for (Round round : rounds) {
            if(!round.isComplete()) {
                gamePlayable = true;
            } else if(!round.isComplete() && ((numberOfOMoves + numberOfXMoves) == 9)) {
                gamePlayable = false;
                playCongratulationAnimation(round);
                break;
            }
            else if(round.isComplete()) {
                gamePlayable = false;
                playCongratulationAnimation(round);
                break;
            }
        }
    }

    public void playCongratulationAnimation(Round round) {
            prepareAnimationLine(round);
            prepareGameStats(round);
    }

    public void prepareGameStats(Round round) {
        String winnerSymbol = round.getWinnerSymbol();
        Text winText = new Text("The winner is: " + winnerSymbol + " , congratulations! Rematch?");
        winText.setFont(new Font(25));
        winText.setFill(Color.DARKGOLDENROD);

        Text scoreX = new Text("X points: " + getStatsX(round));
        Text scoreO = new Text("O points: " + getStatsO(round));
        scoreX.setFont(Font.font(25));
        scoreO.setFont(Font.font(25));
        scoreX.setFill(Color.BLACK);
        scoreO.setFill(Color.BLACK);
        scoreX.setLayoutY(570);
        scoreO.setLayoutY(550);
        scoreO.setLayoutX(10);
        scoreX.setLayoutX(10);

        root.getChildren().addAll(winText, scoreO, scoreX);
    }

    public void prepareAnimationLine(Round round) {
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

    public  class Round {
        public Tile[] tiles;

        public Round(Tile... tiles) { // 3 elements: Tile[0] is horizontal, Tile[1] is vertical, Tile [2] is diagonal
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if(tiles[0].getValue().isEmpty() || tiles[1].getValue().isEmpty() || tiles[2].getValue().isEmpty()) {
                return false;
            } else return tiles[0].getValue().equals(tiles[1].getValue()) &&
                    tiles[0].getValue().equals(tiles[2].getValue());
        }

        public String getWinnerSymbol(){
            String theWinner = "DRAW";
            if (isComplete() || ((numberOfOMoves + numberOfXMoves) == 9)) {
                theWinner = tiles[0].getValue();
            }
            return theWinner;
        }
    }

    public int getStatsX(Round round) {
        return statsX(round);
    }

    public int getStatsO(Round round) {
        return statsO(round);
    }

    public int statsX(Round round) {
        if(round.getWinnerSymbol().equals("X")) {
            xPlayerPoints++;
        }
        return xPlayerPoints;
    }

    public int statsO(Round round) {
        if(round.getWinnerSymbol().equals("O")) {
            oPlayerPoints++;
        }
        return oPlayerPoints;
    }

    public class Tile extends StackPane {
        public Text text = new Text();

        public Tile() {
            Rectangle chart = new Rectangle(200, 200, Color.GREEN);
            chart.setStroke(Color.WHITE);
            chart.setStrokeWidth(8);
            setAlignment(Pos.CENTER);
            text.setFont(new Font(80));
            text.setFill(Color.YELLOW);
            getChildren().addAll(chart, text);
            makeMove();
        }

        public void makeMove() {
            if(!gamePlayable)
                return;
            setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY && !(getValue().equals("O")) && !(getValue().equals("X"))) {
                    if (!playerXMovement) {
                        return;
                    }
                    else {
                        playerMove();
                    }
                }
                if(!gamePlayable)
                    return;
                cpuMove(board);
            });
        }

        public void playerMove() {
            numberOfXMoves++;
            drawX();
            playerXMovement = false;
            checkWinner();
            text.setText("X");
        }

        public void cpuMove(Tile[][] tiles){
            if(playerXMovement)
                return;
            List <Tile> remainedTiles = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(tiles[i][j] != null && tiles[i][j].getValue().isEmpty()) {
                        remainedTiles.add(tiles[i][j]);
                    }
                }
            }
            if (remainedTiles.size() > 0) {
                int a = RANDOM.nextInt(remainedTiles.size());
                Tile chosenTile = remainedTiles.get(a);
                chosenTile.drawO();
                chosenTile.text.setText("O");
                numberOfOMoves++;
                playerXMovement = true;
                checkWinner();
            }
        }

        public void clearBoard(Tile[][] board, Tile[] tiles ) {
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    root.getChildren().clear();
                    board[i][j] = new Tile();
                    board[i][j].text.setText(" ");
                    board[i][j].getChildren().clear();
                }
            }

            for (int i = 0; i < tiles.length; i++) {
                tiles[i].text.setText(" ");
            }
            rounds = new ArrayList<>();
            gamePlayable = true;
            playerXMovement = true;
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
    }
}
