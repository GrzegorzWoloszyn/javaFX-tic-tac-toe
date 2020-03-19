package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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


public class Main extends Application {

    private Pane root = new Pane();
    private boolean turnXOrY = true;
    private boolean playable = true;
    private Tile[][] board = new Tile[3][3];
    private List<Round> rounds = new ArrayList<>();

    private Parent createContent() {
        root.setPrefSize(610, 610);

        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile();
                tile.setTranslateX(col * 200);
                tile.setTranslateY(row * 200);

                root.getChildren().add(tile);

                board[col][row] = tile;
            }
        }

        for(int y = 0; y < 3; y++) {
            rounds.add(new Round(board[0][y], board[1][y], board[2][y]));
        }

        for(int x = 0; x < 3; x++) {
            rounds.add(new Round(board[x][0], board[x][1], board[x][2]));
        }

        rounds.add(new Round(board[0][0], board[1][1], board[2][2]));
        rounds.add(new Round(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        primaryStage.setTitle("Tic-tac-toe");
    }

    public void checkWinner() {

        for (Round round : rounds) {
            if(round.isWon()) {
                playable = false;
                playCongratsAnimation(round);
                break;
            } playable = true;
        }

    }

    private void playCongratsAnimation(Round round) {
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

    }
    public enum Mark{
        X,
        O,
        EMPTY;
    }

    public class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle chart = new Rectangle(200, 200);
            chart.setFill(Color.GREEN);
            chart.setStroke(Color.WHITE);
            chart.setStrokeWidth(10);

            setAlignment(Pos.CENTER);
            getChildren().addAll(chart);

            setOnMouseClicked(mouseEvent -> {
                if(!playable)
                    return;
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                                if (!turnXOrY)
                                    return;
                                drawX();
                                turnXOrY = false;
                                checkWinner();

                    } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            if(turnXOrY) {
                                return; }
                                drawO();
                            turnXOrY = true;
                            checkWinner();

                            }

                    text.setFont(new Font(80));
                    text.setFill(Color.YELLOW);
                    getChildren().add(text);
                });

        }

        private void drawX() {
            text.setText("X");
            setAlignment(Pos.CENTER);
        }

        private void drawO() {
            setAlignment(Pos.CENTER);
            text.setText("O");
        }

        public String getValue() {
            return text.getText();
        }

        public double getCentreX() {
            return getTranslateX() + 110;
        }

        public double getCentreY() {
            return getTranslateY() + 110;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
