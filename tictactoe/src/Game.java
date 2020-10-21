public class Game {
    private Board board;
    private boolean running;
    private Integer round;

    public Game() {
        this.board = new Board();
        this.board.initBoard();
        this.running = true;
        this.round = 0;
    }

    public boolean movePlayer(Symbol player, Position position) {
        return this.board.addEntryToBoard(player, position);
    }

    public boolean getRunning() {
        return this.running;
    }

    public void haltGame() {
        System.out.println("Game is halting...");
        this.running = false;
    }

    public Integer getRound() {
        return this.round;
    }

    public void incrementRound() {
        this.round++;
    }

    public Board getBoard() {
        return this.board;
    }
}
