import java.util.Scanner;

public class Main {

    public static int evaluate(Board board, int depth) {
        if (board.isWinningConfiguration() == Symbol.NOUGHT) {
            return 10 + depth;
        } else if (board.isWinningConfiguration() == Symbol.CROSS) {
            return -10 - depth;
        }
        return 0;
    }

    public static Integer minimax(Board board, int depth, Integer alpha, Integer beta, boolean maximizingPlayer) {
        Integer numberOfPositions = board.getPositions().size();
        if (depth == 0 || board.isFull() || board.isWinningConfiguration() != null) {
            return evaluate(board, depth);
        }

        if (maximizingPlayer) {
            Integer maxEval = Integer.MIN_VALUE;
            for (Integer i = 0; i < numberOfPositions; i++) {
                if (board.getPositions().get(i).getContent() == null) {
                    board.setPosition(Symbol.NOUGHT, new Position(i / 3, i % 3));
                    Integer eval = minimax(board, depth - 1, alpha, beta, false);
                    maxEval = Math.max(maxEval, eval);
                    board.setPosition(null, new Position(i / 3, i % 3));
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return maxEval;
        } else {
            Integer minEval = Integer.MAX_VALUE;
            for (Integer i = 0; i < numberOfPositions; i++) {
                if (board.getPositions().get(i).getContent() == null) {
                    board.setPosition(Symbol.CROSS, new Position(i / 3, i % 3));
                    Integer eval = minimax(board, depth - 1, alpha, beta, true);
                    minEval = Math.min(minEval, eval);
                    board.setPosition(null, new Position(i / 3, i % 3));
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minEval;
        }
    }

    public static Position findBestMove(Board board, int depth) {
        Integer numberOfPositions = board.getPositions().size();
        Integer bestVal = Integer.MIN_VALUE;
        Position bestMove = new Position();

        for (Integer i = 0; i < numberOfPositions; i++) {
            if (board.getPositions().get(i).getContent() == null) {
                board.setPosition(Symbol.NOUGHT, new Position(i / 3, i % 3));
                Integer moveVal = minimax(board, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                board.setPosition(null, new Position(i / 3, i % 3));
                if (moveVal > bestVal) {
                    bestMove.setX(i / 3);
                    bestMove.setY(i % 3);
                    bestVal = moveVal;
                }
            }
        }

        return bestMove;
    }

    public static void main(String[] args) {

        System.out.println("Starting game...");
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        boolean correctMove;
        while (game.getRunning()) {
            correctMove = false;
            Symbol player;
            if (game.getRound() % 2 == 0) {
                player = Symbol.CROSS;
                while (!correctMove) {
                    Integer posX = in.nextInt();
                    Integer posY = in.nextInt();
                    correctMove = game.movePlayer(player, new Position(posX, posY));
                }
            } else {
                player = Symbol.NOUGHT;
                game.movePlayer(player, findBestMove(game.getBoard(), 9));
            }

            game.getBoard().drawBoard();
            if (game.getBoard().isFull() || (game.getBoard().isWinningConfiguration() != null))  {
                game.haltGame();
                if (game.getBoard().isFull()) {
                    System.out.println("Game ended. No winner...");
                } else if (game.getBoard().isWinningConfiguration() == Symbol.CROSS) {
                    System.out.println("Game ended. Player 1 wins...");
                } else {
                    System.out.println("Game ended. Player 2 wins...");
                }
            }
            game.incrementRound();
        }
    }
}
