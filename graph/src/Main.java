import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // Static function to perform BPF. Runtime analysis: O(V + E)
    public static void performBFS(Graph<Vertex<Integer>> graph, Vertex<Integer> source) {
        System.out.println("Performing BFS...");
        System.out.println("D for vertex " + source.getValue() + " is " + source.getD());
        for (Vertex<Integer> v : graph.getMap().get(source)) {
            System.out.println("D for vertex " + v.getValue() + " is " + v.getD());
        }
        for (Vertex<Integer> u : graph.getMap().keySet()) {
            // Ignore if vertex is source
            if (u.getValue() == source.getValue()) {
                continue;
            }

            u.setColor(ColorBFS.WHITE);
            u.setD(Integer.MAX_VALUE);
            u.setPi(null);
        }

        source.setColor(ColorBFS.GRAY);
        source.setD(0);
        source.setPi(null);
        Queue<Vertex<Integer>> Q = new LinkedList<Vertex<Integer>>();
        Q.add(source);
        while(!Q.isEmpty()) {
            Vertex<Integer> u = Q.poll();
            for (Vertex<Integer> v : graph.getMap().get(u)) {
                if (v.getColor() == ColorBFS.WHITE) {
                    v.setColor(ColorBFS.GRAY);
                    v.setD(u.getD() + 1);
                    v.setPi(u);
                    Q.add(v);
                }
            }
            u.setColor(ColorBFS.BLACK);
        }

        System.out.println("BFS computation has ended.\nPrinting results...");
        for (Vertex<Integer> v : graph.getMap().keySet()) {
            System.out.println("D for vertex " + v.getValue() + " is " + v.getD());
        }

        return;
    }

    public static void printPathBFS(Graph<Vertex<Integer>> graph, Vertex<Integer> source, Vertex<Integer> v) {
        if (v.getValue() == source.getValue()) {
            System.out.print(source.getValue() + " ");
        } else if (v.getPi() == null) {
            System.out.println("No path from " + source.getValue() + " to " + v.getValue() + " exists");
        } else {
            printPathBFS(graph, source, v.getPi());
            System.out.print(v.getValue() + " ");
        }
    }

    public static void main(String[] args) {

        Graph<Vertex<Integer>> graph = new Graph<Vertex<Integer>>();
        Vertex<Integer> v1 = new Vertex<Integer>(1, ColorBFS.WHITE, 0);
        Vertex<Integer> v2 = new Vertex<Integer>(2, ColorBFS.WHITE, 0);
        Vertex<Integer> v3 = new Vertex<Integer>(3, ColorBFS.WHITE, 0);
        Vertex<Integer> v4 = new Vertex<Integer>(4, ColorBFS.WHITE, 0);

        Graph<Vertex<String>> graph2 = new Graph<>();
        Vertex<String> shirt = new Vertex<>("shirt", ColorBFS.WHITE, 0);
        Vertex<String> tie = new Vertex<>("tie", ColorBFS.WHITE, 0);
        Vertex<String> jacket = new Vertex<>("jacket", ColorBFS.WHITE, 0);
        Vertex<String> belt = new Vertex<>("belt", ColorBFS.WHITE, 0);
        Vertex<String> watch = new Vertex<>("watch", ColorBFS.WHITE, 0);
        Vertex<String> undershorts = new Vertex<>("undershorts", ColorBFS.WHITE, 0);
        Vertex<String> pants = new Vertex<>("pants", ColorBFS.WHITE, 0);
        Vertex<String> shoes = new Vertex<>("shoes", ColorBFS.WHITE, 0);
        Vertex<String> socks = new Vertex<>("socks", ColorBFS.WHITE, 0);

        graph.addEdge(v1, v2, true);
        graph.addEdge(v1, v3, true);
        graph.addEdge(v2, v3, true);
        graph.addEdge(v2, v4, true);

        graph2.addEdge(shirt, tie, false);
        graph2.addEdge(shirt, belt, false);
        graph2.addEdge(tie, jacket, false);
        graph2.addEdge(belt, jacket, false);
        graph2.addEdge(undershorts, pants, false);
        graph2.addEdge(pants, belt, false);
        graph2.addEdge(undershorts, shoes, false);
        graph2.addEdge(pants, shoes, false);
        graph2.addEdge(socks, shoes, false);



//        performBFS(graph, v1);
//        printPathBFS(graph, v1, v4);
        DFS dfs = new DFS(0);
        dfs.performDFS(graph);

    }
}
