public class Position {
    private Integer x, y;
    private Symbol content;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Symbol getContent() {
        return this.content;
    }

    public void setContent(Symbol content) {
        this.content = content;
    }
}
