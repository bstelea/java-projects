import java.util.ArrayList;

public class Board {
    private ArrayList<Position> positions = new ArrayList();

    public Board() {}

    public void initBoard() {
        for (Integer x = 0; x < 3; x++) {
            for (Integer y = 0; y < 3; y++) {
                positions.add(new Position(x, y));
            }
        }
    }

    public boolean addEntryToBoard(Symbol symbol, Position position) {
        boolean response = false;
        // Only add entry to cell if cell is empty
        if (positions.get(3 * position.getX() + position.getY()).getContent() == null) {
            positions.get(3 * position.getX() + position.getY()).setContent(symbol);
            response = true;
        }
        return response;
    }

    public void drawBoard() {
        System.out.println("Draw board...");
        for (Integer x = 0; x < 3; x++) {
            for (Integer y = 0; y < 3; y++) {
                if (positions.get(3 * x + y).getContent() != null) {
                    if (positions.get(3 * x + y).getContent() == Symbol.CROSS) {
                        System.out.print("X ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.print("\n");
        }
        return;
    }

    public boolean isFull() {
        for (Integer x = 0; x < 3; x++) {
            for (Integer y = 0; y < 3; y++) {
                if (positions.get(3 * x + y).getContent() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Symbol isWinningConfiguration() {
        // Check if full row
        for (Integer x = 0; x < 3; x++) {
            if ((positions.get(3 * x + 0).getContent() != null) && (positions.get(3 * x + 0).getContent() == positions.get(3 * x + 1).getContent()) && (positions.get(3 * x + 1).getContent()  == positions.get(3 * x + 2).getContent())) {
                return positions.get(3 * x + 0).getContent();
            }
        }

        // Check if full col
        for (Integer y = 0; y < 3; y++) {
            if ((positions.get(3 * 0 + y).getContent() != null) && (positions.get(3 * 0 + y).getContent() == positions.get(3 * 1 + y).getContent()) && (positions.get(3 * 1 + y).getContent() == positions.get(3 * 2 + y).getContent())) {
                return positions.get(3 * 0 + y).getContent();
            }
        }

        // Check if primary diagonal
        if ((positions.get(3 * 0 + 0).getContent() != null) && (positions.get(3 * 0 + 0).getContent() == positions.get(3 * 1 + 1).getContent()) && (positions.get(3 * 1 + 1).getContent() == positions.get(3 * 2 + 2).getContent())) {
            return positions.get(3 * 0 + 0).getContent();
        }

        // Check if secondary diagonal
        if ((positions.get(3 * 0 + 2).getContent() != null) && (positions.get(3 * 0 + 2).getContent() == positions.get(3 * 1 + 1).getContent()) && (positions.get(3 * 1 + 1).getContent() == positions.get(3 * 2 + 0).getContent())) {
            return positions.get(3 * 0 + 2).getContent();
        }

        return null;
    }

    public ArrayList<Position> getPositions() {
        return this.positions;
    }

    public void setPosition(Symbol player, Position position) {
        this.positions.get(3 * position.getX() + position.getY()).setContent(player);
    }
}
