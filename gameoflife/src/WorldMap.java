import java.util.ArrayList;

public class WorldMap {
    private int rows, cols;
    private ArrayList<Cell> cells, cells2;

    public WorldMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initMap();
    }

    public void initMap() {
        this.cells = new ArrayList<>();
        this.cells2 = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                cells.add(new Cell(State.DEAD, i, j));
                cells2.add(new Cell(State.DEAD, i, j));
            }
        }
        cells.get(0 * this.cols + 2).setState(State.LIVE);
        cells.get(1 * this.cols + 3).setState(State.LIVE);
        cells.get(2 * this.cols + 1).setState(State.LIVE);
        cells.get(2 * this.cols + 2).setState(State.LIVE);
        cells.get(2 * this.cols + 3).setState(State.LIVE);
        return;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public void drawMap() {
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < this.cols; i++) {
            System.out.print("+-");
        }
        System.out.print("+\n");

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print("|");
                if (cells.get(i * this.cols + j).getState() == State.DEAD) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }

            System.out.print("|\n");

            for (int k = 0; k < this.cols; k++) {
                System.out.print("+-");
            }
            System.out.print("+\n");
        }
    }

    private void updateCells(ArrayList<Cell> cells, ArrayList<Cell> cells2) {
        int size = cells.size();
        for (int i = 0; i < size; i++) {
            cells2.get(i).setState(cells.get(i).getState());
        }
        return;
    }

    private int getNorth(int i, int j) {
        return (i == 0) ? ((this.rows - 1) * this.cols + j) : ((i - 1) * this.cols + j);
    }

    private int getEast(int i, int j) {
        return (j == this.cols - 1) ? (i * this.cols) : (i * this.cols + (j + 1));
    }

    private int getSouth(int i, int j) {
        return (i == this.rows - 1) ? j : ((i + 1) * this.cols + j);
    }

    private int getWest(int i, int j) {
        return (j == 0) ? (i * this.cols + (this.cols - 1)) : (i * this.cols + (j - 1));
    }

    public void evolveMap() {
        updateCells(cells, cells2);
        int liveNeighbourCount;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                liveNeighbourCount = 0;
                ArrayList<Integer> neighbours = new ArrayList<>();
                int north, northeast, east, southeast, south, southwest, west, northwest;

//                north = (i == 0) ? ((this.rows - 1) * this.cols + j) : ((i - 1) * this.cols + j);
//                east = (j == this.cols - 1) ? (i * this.cols) : (i * this.cols + (j + 1));
//                south = (i == this.rows - 1) ? j : ((i + 1) * this.cols + j);
//                west = (j == 0) ? (i * this.cols + (this.cols - 1)) : (i * this.cols + (j - 1));

                neighbours.add(getNorth(i, j));
                neighbours.add(getEast(i, j));
                neighbours.add(getSouth(i, j));
                neighbours.add(getWest(i, j));
                neighbours.add(getNorth(getEast(i, j) / this.cols, getEast(i, j) % this.cols));
                neighbours.add(getSouth(getEast(i, j) / this.cols, getEast(i, j) % this.cols));
                neighbours.add(getSouth(getWest(i, j) / this.cols, getWest(i, j) % this.cols));
                neighbours.add(getNorth(getWest(i, j) / this.cols, getWest(i, j) % this.cols));
//                System.out.println("For position " + i + ", " + j + " north is: " + north + " (" + cells2.get(north).getState() + ") " + " east is: " + east + " (" + cells2.get(east).getState() + ") " + " south is: " + south + " (" + cells2.get(south).getState() + ") " + " west is: " + west + " (" + cells2.get(west).getState() + ") ");

                for (int index = 0; index < neighbours.size(); index++) {
                    if (cells2.get(neighbours.get(index)).getState() == State.LIVE) {
                        liveNeighbourCount++;
                    }
                }
//                System.out.println("liveNeighbourCount for cell " + i + ", " + j + " is: " + liveNeighbourCount);

                if (cells2.get(i * this.cols + j).getState() == State.DEAD && liveNeighbourCount == 3) {
                    this.cells.get(i * this.cols + j).setState(State.LIVE);
                } else if (cells2.get(i * this.cols + j).getState() == State.LIVE && (liveNeighbourCount != 2 && liveNeighbourCount != 3)) {
                    this.cells.get(i * this.cols + j).setState(State.DEAD);
                }

            }
        }
    }
}
