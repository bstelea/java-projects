import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    private Map<T, List<T>> map = new HashMap<>();

    public Map<T, List<T>> getMap() {
        return this.map;
    }

    public void addVertex(T vertex) {
        map.put(vertex, new LinkedList<T>());
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
        // Check if source vertex is in the graph adj list
        if (!map.containsKey(source)) {
            addVertex(source);
        }

        // Check if destination vertex is in the graph adj list
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }

        // Only add the edge in the map if it was not added prev
        if (!map.get(source).contains(destination)) {
            map.get(source).add(destination);
        }

        // Add the reverse edge in the map if edge is bidirectional and edge was not added prev
        if (bidirectional && !map.get(destination).contains(source)) {
            map.get(destination).add(source);
        }

        return;
    }

    public void getVertexCount() {
        System.out.println("Graph has " + map.keySet().size() + " vertices.");
    }

    public void getEdgeCount(boolean bidirection) {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }

        if (bidirection) {
            count /= 2;
        }

        System.out.println("Graph has " + count + " edges.");
        return;
    }

    public boolean hasVertex(T vertex) {
        return map.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        return map.get(source).contains(destination);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
