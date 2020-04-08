package sample;

import javafx.scene.text.Text;

public class Symbol {
    private String symbol;
    Text text;

    public Symbol() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void drawSymbol(String s) {
        text.setText(s);
    }

}
