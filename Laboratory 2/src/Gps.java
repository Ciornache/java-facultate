import java.util.*;
public interface Gps {

    int findBestRoad(Location x, Location y);

    List<Road> getBestRoad(Location x, Location y);

    boolean isRoad(Location x, Location y);

}
