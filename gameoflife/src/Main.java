import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        WorldMap map = new WorldMap(5, 5);

        while (true) {
            map.drawMap();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
                System.err.print(e);
            }
            map.evolveMap();
        }
    }
}
