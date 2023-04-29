import java.util.*;


public class Main {

    public static void main(String [] args)
    {
        Waze gps = new Waze();
        OutputHandler outputHandler = new OutputHandler();

        int queries = InputReader.scanner.nextInt();

        for(int q = 1; q <= queries; ++q)
        {
            Location location1 = readLocationByName(InputReader.scanner, gps);
            Location location2 = readLocationByName(InputReader.scanner, gps);

            int distance = gps.findBestRoad(location1, location2);

            outputHandler.printDistance(location1, location2, distance);
        }
        System.out.println("All queries read");
    }

    private static Location readLocationByName(Scanner scanner, Waze gps)
    {
        while(true) {
            String name = scanner.next();
            Location location = gps.getLocationForName(name);
            if (location != null)
                return location;
        }
    }

}