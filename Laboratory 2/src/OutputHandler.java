import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OutputHandler {

    public void printSimplePath(Location x, Location y, List<pair> road)
    {
        if(road == null)
        {
            System.out.println("It doesn't exist a path between the location " + x.getName() + " and " + y.getName() + "\n");
            return;
        }

        System.out.println("The path between " + x.getName() + " and " + y.getName() + " is");
        System.out.print(x.getName() + " ->");
        for(int i = 1; i < road.size(); ++i)
            System.out.print(" " + Waze.getNameForLocation(road.get(i).getNode()) + " ->");
        System.out.println(" " + y.getName() + "\n");
    }

    public void printComplexePath(Location x, Location y, List<pair> road)
    {
        if(road == null)
        {
            System.out.println("It doesn't exist a path between the location " + x.getName() + " and " + y.getName() + "\n");
            return;
        }

        System.out.println("The path between " + x.getName() + " and " + y.getName() + " is");
        System.out.print(x.getName() + " ->");
        for(int i = 1; i < road.size(); ++i)
            System.out.print(" " + Waze.getNameForLocation(road.get(i).getNode()) + " ->");
        System.out.println(" " + y.getName() + "\n");
    }

    public void printDistance(Location x, Location y, double dist)
    {
        if(dist == -1)
        {
            System.out.println("It doesn't exist a path between the location " + x.getName() + " and " + y.getName() + "\n");
            return;
        }

        String firstName = x.getName(), secondName = y.getName();

        String firstLocationType = Waze.findTypeOfLocation(x);
        String secondLocationType = Waze.findTypeOfLocation(y);

        System.out.println("The distance between " + firstLocationType + " " +
                firstName + " and " + secondLocationType + " " +  secondName + " is " + dist + "\n");
    }

    public void printPathExistence(boolean exist, Location location1, Location location2)
    {
        if(exist){
            System.out.println("There exists a path between location " +
                    location1.getName() + " and " + location2.getName() + "\n");
        }
        else
        {
            System.out.println("There doesn't exist a path between location "
                    + location1.getName() + " and " + location2.getName() + "\n");
        }

    }

    public void printProblemInstance(Waze gps) {
        System.out.println("The given locations are");
        gps.printLocations();
        System.out.println();
        System.out.println("The relationships between them are described using the roads");
        gps.printRoads();
        System.out.println("The purpose of this problem is to determine 3 possible properties : \n" +
                "1. The minimum amount of time it takes to travel from Location x to Location y\n" +
                "2. The precise path between a Location x and a Location y\n" +
                "3. The existence of a path between a Location x and a Location y\n");
    }

    public void printConnectedComponents(ArrayList<ArrayList<String>> connectedComponents)
    {
        if(connectedComponents == null)
        {
            System.out.println("There are no connected components in the entire graph\n");
            return;
        }
        System.out.println("There are a total of " + connectedComponents.size() + " connected components\n");

        for (ArrayList<String> connectedComponent : connectedComponents) {
            if(connectedComponent.size() == 0)
                continue;
            for (String string : connectedComponent)
                System.out.print(string + "->");
            System.out.println("END");
        }
        System.out.println();
    }

}
