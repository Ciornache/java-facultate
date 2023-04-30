import java.util.List;

public class OutputHandler {

    public void printSimplePath(Location x, Location y, List<pair> road)
    {
        if(road.size() == 0)
        {
            System.out.println("It doesn't exist a path between the two location" + "\n");
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
        if(road.size() == 0)
        {
            System.out.println("It doesn't exist a path between the two location" + "\n");
            return;
        }

        System.out.println("The path between " + x.getName() + " and " + y.getName() + " is");
        System.out.print(x.getName() + " ->");
        for(int i = 1; i < road.size(); ++i)
            System.out.print(" " + Waze.getNameForLocation(road.get(i).getNode()) + " ->");
        System.out.println(" " + y.getName() + "\n");
    }

    public void printDistance(Location x, Location y, int dist)
    {
        if(dist == -1)
        {
            System.out.println("It doesn't exist a path between the two location" + "\n");
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

}
