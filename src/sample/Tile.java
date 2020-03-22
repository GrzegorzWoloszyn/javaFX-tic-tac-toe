package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Tile extends StackPane {

    public Pane root = new Pane();
    public Text text = new Text();
    public int moveX = 0;
    public int moveO = 0;
    public int move = moveO + moveX;
    public boolean turnXOrY = true;
    public boolean playable = true;

    public List<Round> rounds = new ArrayList<>();


        public Tile() {
            Rectangle chart = new Rectangle(200, 200, Color.GREEN);
            chart.setStroke(Color.WHITE);
            chart.setStrokeWidth(10);

            setAlignment(Pos.CENTER);
            getChildren().addAll(chart);

            setOnMouseClicked(mouseEvent -> {
                if(!playable)
                    return;
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                                if (!turnXOrY) {
                                    return;
                                }else if(text.getText() == "O"){
                                 return;
                                }
                                else {
                                drawX();
                                moveX++;
                                turnXOrY = false;
                                checkWinner(rounds);
                                 }

                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                                if (!turnXOrY) {
                                    return;
                                } else if(text.getText() == "X"){
                                    return;
                                 }
                                else {
                                drawO();
                                moveO++;
                                turnXOrY = true;
                                checkWinner(rounds);
                                }
                         }

                text.setFont(new Font(80));
                text.setFill(Color.YELLOW);
                getChildren().add(text);
            });

        }

        public void drawX() {
            text.setText("X");
            setAlignment(Pos.CENTER);
        }

        public void drawO() {
            text.setText("O");
            setAlignment(Pos.CENTER);
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

    public void checkWinner(List<Round> rounds) {
        for (Round round : rounds) {
            if(round.isWon()) {
                playable = false;
                playCongratsAnimation(round);
                break;
            } playable = true;
        }

    }

    public void playCongratsAnimation(Round round) {

        Text winText = new Text("The winner is: " + round.getWinnerSymbol() + " , congratulations! Rematch?" );
        winText.setFont(new Font(25));
        winText.setFill(Color.DARKGOLDENROD);

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
        } else{}
        root.getChildren().add(winText);

    }

}

