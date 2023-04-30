import java.util.*;
public interface Gps {

    int findBestRoad(Location x, Location y);

    List<pair> getBestRoad(Location x, Location y);

    boolean isRoad(Location x, Location y);

}
