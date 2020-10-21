public class Cell {
    private State state;
    int x, y;

    public Cell(State state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
    }

    public Cell() {
        this.state = State.DEAD;
        this.x = 0;
        this.y = 0;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
