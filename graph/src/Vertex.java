public class Vertex<T> {
    private ColorBFS color;
    private Integer d, f;
    private Vertex pi;
    private T value;

    public Vertex(T value, ColorBFS color, Integer d) {
        this.value = value;
        this.color = ColorBFS.WHITE;
        this.d = d;
    }

    public Vertex() {
        return;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ColorBFS getColor() {
        return this.color;
    }

    public void setColor(ColorBFS color) {
        this.color = color;
    }

    public Integer getD() {
        return this.d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public Integer getF() {
        return this.f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Vertex getPi() {
        return this.pi;
    }

    public void setPi(Vertex pi) {
        this.pi = pi;
    }

}
