package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Board {

    private Image imageBack = new Image("sample/resource/tictactoeBoard.png");
    //private Image welcomeImage = new Image("sample/resource/welcomeBoard.png");
    private Label welcomeLabel = new Label("Welcome to Tic-Tac-Toe");
    private Button buttonPlay = new Button();

    private TextField textField = new TextField();

    public void setPlayBoard(Stage primaryStage) {
        BackgroundSize backgroundSize = new BackgroundSize(550, 450, true, true, true, false);
        BackgroundImage backgroundPlayImage = new BackgroundImage(imageBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background backgroundPlay = new Background(backgroundPlayImage);

        buttonPlay.setText("Play");
        textField.setText("Enter Your name: ");

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        welcomeLabel.setAlignment(Pos.TOP_CENTER);
        welcomeLabel.setFont(new Font("System Bold", 25));
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setPrefSize(700, 100);
        welcomeLabel.setPadding(new Insets(10, 10, 0, 10));
        grid.add(welcomeLabel, 1  , 5);
        //grid.getChildren().add(welcomeLabel);

        textField.setAlignment(Pos.CENTER);
        textField.setFont(new Font("System Bold", 25));
        textField.setPrefSize(200, 50);
        grid.add(textField, 5, 15);
        //grid.getChildren().add(textField);

        buttonPlay.setAlignment(Pos.BOTTOM_RIGHT);

        buttonPlay.setPrefSize(50, 20);
        buttonPlay.setFont(new Font("System Bold", 15));
        grid.add(buttonPlay, 10  , 5);
        //grid.getChildren().add(buttonPlay);

        grid.setBackground(backgroundPlay);
        primaryStage.setX(450);
        primaryStage.setY(130);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(grid, 600, 600, Color.GOLD);
        primaryStage.setTitle("Tic-tac-toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
