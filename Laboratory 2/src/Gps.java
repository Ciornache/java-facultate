import java.util.*;
public interface Gps {

    /**
     *
     * @param x - represents the first location(it can be anything from a city to a school)
     * @param y - represents the first location(it can be anything from a city to a school)
     * @return -  the function returns the minimum distance between location x and location y,
     *            using the roads that are given in the input
     *
     *           For example if the roads are Iasi Bucuresti with a distance of 2
     *                                    and Bucuresti Craiova with a distance of 5
     *           the method is going to return 7, representing the distance between Iasi and Craiova
     *
     *           Note : This is a simplified version of the algorithm,
     *                  the full version handles road types and speed limits, the answers being returned in hours
     */
    double findBestRoad(Location x, Location y);

    /**
     *
     * @param x - represents the first location(it can be anything from a city to a school)
     * @param y - represents the first location(it can be anything from a city to a school)
     * @return - the function returns the minimum time savior path between the location x and location y
     *           using the road that are given in the input
     *
     *           For example if the roads are Iasi Bucuresti
     *                                    and Bucuresti Craiova
     *           the method is going to return Iasi -> Bucuresti -> Craiova, representing the path
     *           between Iasi and Craiova
     *
     *           Note: Check the note above
     *
     */

    List<pair> getBestRoad(Location x, Location y);

    /**
     *
     * @param x - represents the first location(it can be anything from a city to a school)
     * @param y - represents the first location(it can be anything from a city to a school)
     * @return -  the function return the existence of a road between the first and second location
     *            1 - there is a location
     *            0 - the location doesn't exist
     */

    boolean isRoad(Location x, Location y);

    ArrayList<ArrayList<String>> calculateConnectedComponents();

}
