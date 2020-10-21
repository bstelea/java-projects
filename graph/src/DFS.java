import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DFS {
    private Integer time;

    public DFS(Integer time) {
        this.time = time;
    }

    public DFS() {
        this.time = 0;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void performDFS(Graph<Vertex<Integer>> graph) {
        for (Vertex<Integer> u : graph.getMap().keySet()) {
            u.setColor(ColorBFS.WHITE);
            u.setPi(null);
        }
        this.setTime(0);

        // Sort vertices by value
        ArrayList<Vertex<Integer>> keys = new ArrayList<>(graph.getMap().keySet());
        keys.sort(Comparator.comparing(Vertex::getValue));

        for (Vertex<Integer> u : keys) {
            if (u.getColor() == ColorBFS.WHITE) {
                DFSVisit(graph, u);
            }
        }
        System.out.println("DFS computation done.\nPrinting results...");
        for (Vertex<Integer> v : keys) {
            System.out.println("Vertex " + v.getValue() + " " + v.getD() + "/" + v.getF());
        }
    }

    private void DFSVisit(Graph<Vertex<Integer>> graph, Vertex<Integer> u) {
        this.setTime(this.getTime() + 1);
        u.setD(this.getTime());
        u.setColor(ColorBFS.GRAY);
        for (Vertex<Integer> v : graph.getMap().get(u)) {
            if (v.getColor() == ColorBFS.WHITE) {
                v.setPi(u);
                DFSVisit(graph, v);
            }
        }
        u.setColor(ColorBFS.BLACK);
        this.setTime(this.getTime() + 1);
        u.setF(this.getTime());
    }
}
