//package sample;
//
//import javafx.geometry.Pos;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import sample.resource.Owner;
//
//public class Tile extends StackPane {
//    public Text text = new Text();
//    private Owner ownerPlayer = Owner.EMPTY;
//
//
//    public void playGame() throws Exception {
//        while(playable) {
//            makeMove();
//            Main.Tile tile;
//            cpuMove();
//        }
//    }
//
//    public Tile() throws Exception {
//        Rectangle chart = new Rectangle(200, 200, Color.GREEN);
//        chart.setStroke(Color.WHITE);
//        chart.setStrokeWidth(10);
//        setAlignment(Pos.CENTER);
//        getChildren().addAll(chart, text);
//        //makeMove();
//        playGame();
//        text.setFont(new Font(80));
//        text.setFill(Color.YELLOW);
//    }
//
//    public void makeMove() {
//
//        setOnMouseClicked(mouseEvent -> {
//            if(!playable)
//                return;
//            if (mouseEvent.getButton() == MouseButton.PRIMARY && !(getValue().equals("O"))) {
//                if (!turnXOrY) {
//                    return;
//                }
//                else {
//                    moveX++;
//                    drawX();
//                    turnXOrY = false;
//
//                    checkWinner();
//                }
//            }
//            else if (mouseEvent.getButton() == MouseButton.SECONDARY && !(getValue().equals("X"))) {
//                if (turnXOrY)
//                    return;
//                else {
//                    moveO++;
//                    drawO();
//                    turnXOrY = true;
//                    checkWinner();
//                }
//            }
//        });
//    }
//    public void checkWinner() {
//        for (Main.Round round : rounds) {
//            if(round.isWon()  || (moveX + moveO) == 9) {
//                playable = false;
//                playCongratsAnimation(round);
//                break;
//            }
//            playable = true;
//        }
//    }
//
//    public void cpuMove(Main.Tile tile) throws Exception{
//        if(tile.getOwnerPlayer().equals(Owner.X) || tile.getOwnerPlayer().equals(Owner.O)) {
//            return;
//        } else
//            drawO();
//        tile.setOwnerPlayer(Owner.O);
//        checkWinner();
//    }
//
//    public void drawX() {
//        text.setText("X");
//        setAlignment(Pos.CENTER);
//    }
//
//    public void drawO() {
//        setAlignment(Pos.CENTER);
//        text.setText("O");
//    }
//
//    public String getValue() {
//        return text.getText();
//    }
//
//    public double getCentreX() {
//        return getTranslateX() + 108;
//    }
//
//    public double getCentreY() {
//        return getTranslateY() + 108;
//    }
//
//    public Owner getOwnerPlayer() {
//        return ownerPlayer;
//    }
//
//    public void setOwnerPlayer(Owner ownerPlayer) {
//        this.ownerPlayer = ownerPlayer;
//    }
//}
//
