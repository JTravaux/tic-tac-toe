package org.example;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2 (bot)", 'O');

        Game game = new Game(player1, player2);
        game.playGame();
    }
}