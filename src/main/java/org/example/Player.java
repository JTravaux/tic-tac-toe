package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private boolean isBot;
    private List<Integer> playerPositions;
    private char piece;
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name, char piece) {
        this.name = name;
        this.piece = piece;
        this.playerPositions = new ArrayList<>();
        this.isBot = this.name.toLowerCase().contains("bot");
    }

    public int getMove() {
        if (isBot) return random.nextInt(9) + 1;
        else return scanner.nextInt();
    }

    public char getPiece() {
        return piece;
    }
    public void addPosition(int pos) {
        playerPositions.add(pos);
    }
    public List<Integer> getPositions() {
        return playerPositions;
    }
    public String getName() {
        return name;
    }
    public boolean getIsBot() {
        return isBot;
    }

}
