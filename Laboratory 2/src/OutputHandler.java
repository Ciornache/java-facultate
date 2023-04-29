import java.util.List;

public class OutputHandler {

    public void printSimplePath(Location x, Location y, List<pair> road)
    {
        if(road.size() == 0)
        {
            System.out.println("It doesn't exist a path between the two location");
            return;
        }

        System.out.println("The path between " + x.getName() + " and " + y.getName() + " is");
        System.out.print(x.getName() + " ->");
        for(int i = 1; i < road.size(); ++i)
            System.out.print(" " + Waze.getNameForLocation(road.get(i).getNode()) + " ->");
    }

    public void printComplexePath(Location x, Location y, List<pair> road)
    {
        if(road.size() == 0)
        {
            System.out.println("It doesn't exist a path between the two location");
            return;
        }

        System.out.println("The path between " + x.getName() + " and " + y.getName() + " is");
        System.out.print(x.getName() + " ->");
        for(int i = 1; i < road.size(); ++i)
            System.out.print(" " + Waze.getNameForLocation(road.get(i).getNode()) + " ->");
    }

    public void printDistance(Location x, Location y, int dist)
    {
        if(dist == -1)
        {
            System.out.println("It doesn't exist a path between the two location");
            return;
        }

        String firstName = x.getName(), secondName = y.getName();

        String firstLocationType = Waze.findTypeOfLocation(x);
        String secondLocationType = Waze.findTypeOfLocation(y);

        System.out.println("The distance between " + firstLocationType + " " +
                firstName + " and " + secondLocationType + " " +  secondName + " is " + dist);
    }

}
