//package sample;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.util.Duration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Tile extends StackPane {
//
//    public Pane root = new Pane();
//    public Text text = new Text();
//    private int moveX = 0;
//    private int moveO = 0;
//    private int move = moveO + moveX;
//    private boolean turnXOrY = true;
//    private boolean playable = true;
//    Tile[][] board = new Tile[3][3];
//
//    public List<Round> rounds = new ArrayList<>();
//
//        public Tile() {
//
//            Rectangle chart = new Rectangle(200, 200, Color.GREEN);
//            chart.setStroke(Color.WHITE);
//            chart.setStrokeWidth(10);
//
//            setAlignment(Pos.CENTER);
//            getChildren().addAll(chart);
//
//            text.setFont(new Font(90));
//            text.setFill(Color.YELLOW);
//            getChildren().add(text);
//
//            setOnMouseClicked(mouseEvent -> {
//                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
//                    if (!turnXOrY)
//                        return;
//
//                    drawX();
//                    turnXOrY = false;
//
//                }
//                else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
//                    if (turnXOrY)
//                        return;
//
//                    drawO();
//                    turnXOrY = true;
//                }
//              });
//
//        }
//
//        public void drawX() {
//            text.setText("X");
//            setAlignment(Pos.CENTER);
//        }
//
//        public void drawO() {
//            text.setText("O");
//            setAlignment(Pos.CENTER);
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
//
//    public void checkWinner(List<Round> rounds) {
//        for (Round round : rounds) {
//            if(round.isWon()) {
//                playable = false;
//                playCongratsAnimation(round);
//                break;
//            } playable = true;
//        }
//
//    }
//
//    public void playCongratsAnimation(Round round) {
//
//        Text winText = new Text("The winner is: " + round.getWinnerSymbol() + " , congratulations! Rematch?" );
//        winText.setFont(new Font(25));
//        winText.setFill(Color.DARKGOLDENROD);
//
//        if (round.isWon() || move == 9) {
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
//        root.getChildren().add(winText);
//
//    }
//
//}
//
