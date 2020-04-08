package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private Text text = new Text();
    //Move move = new Move();

    public Tile() {
        Rectangle chart = new Rectangle(200, 200, Color.GREEN);
        chart.setStroke(Color.WHITE);
        chart.setStrokeWidth(8);
        setAlignment(Pos.CENTER);
        text.setFont(new Font(80));
        text.setFill(Color.YELLOW);
        getChildren().addAll(chart, text);
       // move.makeMove();
    }
}

