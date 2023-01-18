package org.example;

import java.util.List;

public class Game {
    private Player player1;
    private Player player2;
    private char[][] gameBoard = {
            {' ', '|',' ','|', ' '},
            {'-', '+','-','+', '-'},
            {' ', '|',' ','|', ' '},
            {'-', '+','-','+', '-'},
            {' ', '|',' ','|', ' '}
    };

    private List<List> winningPositions = List.of(
            List.of(1, 2, 3),
            List.of(4, 5, 6),
            List.of(7, 8, 9),
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(3, 6, 9),
            List.of(1, 5, 9),
            List.of(3, 5, 7)
    );

    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_NORMAL = "\033[0m";

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void printGameBoard() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private void playMove(int pos, Player player) {
        switch (pos) {
            case 1:
                gameBoard[0][0] = player.getPiece();
                break;
            case 2:
                gameBoard[0][2] = player.getPiece();
                break;
            case 3:
                gameBoard[0][4] = player.getPiece();
                break;
            case 4:
                gameBoard[2][0] = player.getPiece();
                break;
            case 5:
                gameBoard[2][2] = player.getPiece();
                break;
            case 6:
                gameBoard[2][4] = player.getPiece();
                break;
            case 7:
                gameBoard[4][0] = player.getPiece();
                break;
            case 8:
                gameBoard[4][2] = player.getPiece();
                break;
            case 9:
                gameBoard[4][4] = player.getPiece();
                break;
            default:
                break;
        }

        player.addPosition(pos);
    }

    private Boolean checkGameOver() {
        for (List winningPosition : winningPositions) {
            if (player1.getPositions().containsAll(winningPosition)) {
                System.out.printf("%s%s won!%s%n", ANSI_GREEN, player1.getName(), ANSI_NORMAL);
                return true;
            } else if (player2.getPositions().containsAll(winningPosition)) {
                System.out.printf("%s%s won!%s%n", ANSI_GREEN, player2.getName(), ANSI_NORMAL);
                return true;
            } else if (player1.getPositions().size() + player2.getPositions().size() == 9) {
                System.out.printf("%sIt's a draw!%s%n", ANSI_GREEN, ANSI_NORMAL);
                return true;
            }
        }

        return false;
    }

    private boolean isMoveValid(int pos) {
        return pos > 0 && pos < 10 && !(player1.getPositions().contains(pos) || player2.getPositions().contains(pos));
    }

    public void playGame() {
        printGameBoard();

        while (true) {
            System.out.printf("%s, it's your move. Enter a number from 1-9: ", player1.getName());

            int player1Move = player1.getMove();
            while (!isMoveValid(player1Move)) {
                System.out.print("Position taken! Choose another move: ");
                player1Move = player1.getMove();
            }

            playMove(player1Move, player1);

            if (checkGameOver()) {
                printGameBoard();
                break;
            } else if (!player2.getIsBot()) {
                printGameBoard();
            }

            if (!player2.getIsBot()) {
                System.out.printf("%s, it's your move. Enter a number from 1-9: ", player2.getName());
            }

            int player2Move = player2.getMove();
            while (!isMoveValid(player2Move)) {
                if (!player2.getIsBot()) {
                    System.out.print("Position taken! Choose another move: ");
                }
                player2Move = player2.getMove();
            }

            playMove(player2Move, player2);

            if (checkGameOver()) {
                printGameBoard();
                break;
            } else {
                printGameBoard();
            }
        }
    }

}
