import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.lang.Math.*;

public class InputVerifier {
    public boolean isAlreadyLocation(Location currentLocation, List<Location> locations)
    {
        for(Location location : locations)
        {
            if(currentLocation.equals(location))
                return false;
        }
        return true;
    }

    public boolean checkLocation(String location, HashMap<String, Integer> hashMap)  {
         return hashMap.get(location)  != null;
    }

    public boolean verifyDistance(String firstName, String secondName, List<Location> locations, int distance) {

        List<Location> twoLocations = new ArrayList<>();

        for(Location loc : locations)
        {
            if(loc.getName().equals(firstName))
                twoLocations.add(loc);
            else if(loc.getName().equals(secondName))
                twoLocations.add(loc);
        }

        int x1 = twoLocations.get(0).getPos().x, y1 = twoLocations.get(0).getPos().y;
        int x2 = twoLocations.get(1).getPos().x, y2 = twoLocations.get(1).getPos().y;

        return calculateDistance(x1, y1, x2, y2) >= distance;

    }

    public double calculateDistance(int x1, int y1, int x2, int y2){
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    boolean isEdgeIn(String location1, String location2, List<List<pair>> graph, HashMap<String, Integer> hashMap)
    {
        int hash1 = hashMap.get(location1), hash2 = hashMap.get(location2);
//        System.out.print(hash1 + " " + hash2 + "\n");
        for(pair p : graph.get(hash1))
        {
            if(p.getNode() == hash2)
                return true;
        }
        return false;
    }

}
