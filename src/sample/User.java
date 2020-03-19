package sample;

import java.util.Random;
import java.util.Scanner;

public class User {

    Scanner scanner = new Scanner(System.in);

    private String name;
    private String symbol = scanner.nextLine();

    public User() {
    }

    public String getName() {

        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName() {
        name = scanner.nextLine();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
