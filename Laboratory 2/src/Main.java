import java.util.*;


public class Main {

    public static void main(String [] args)
    {
        Waze gps = new Waze();
        OutputHandler outputHandler = new OutputHandler();

        gps.printLocations();
        gps.printRoads();

        Scanner mainScanner = gps.inputReader.scanner;

        System.out.print(mainScanner.hashCode() + " " + gps.inputReader.hashCode() + "\n");

        int queries = mainScanner.nextInt();

        for(int q = 1; q <= queries; ++q)
        {
            Location location1 = readLocationByName(mainScanner, gps);
            Location location2 = readLocationByName(mainScanner, gps);

            int type = mainScanner.nextInt();

            switch (type)
            {
                case 1:
                    int distance = gps.findBestRoad(location1, location2);
                    outputHandler.printDistance(location1, location2, distance);
                    break;

                case 2:
                    List<pair> roads = gps.getBestRoad(location1, location2);
                    outputHandler.printSimplePath(location1, location2, roads);
                    break;

                case 3:
                    boolean isPath = gps.isRoad(location1, location2);
                    outputHandler.printPathExistence(isPath, location1, location2);
                    break;
            }
            System.gc();
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


/*

Input test

5
0 Iasi 4 10
0 Constanta 1 10
0 Bacau 5 12
0 Galati 6 4
0 Pocreaca 7 120
6
Iasi Constanta 1 60 2
Constanta Bacau 1 100 1
Galati Iasi 1 54 0
Pocreaca Bacau 1 100 1
Iasi Pocreaca 1 53 2
Galati Pocreaca 1 10 1
3
Constanta Pocreaca 1
Constanta Pocreaca 2
Constanta Pocreaca 3



 */