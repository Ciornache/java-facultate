import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String [] args) throws IOException {

        long startTime = System.currentTimeMillis();

        Waze gps = new Waze();
        OutputHandler outputHandler = new OutputHandler();
        outputHandler.printProblemInstance(gps);

        Scanner mainScanner = gps.inputReader.scanner;

//        System.out.print(mainScanner.hashCode() + " " + gps.inputReader.hashCode() + "\n");

        int queries = mainScanner.nextInt();

        for(int q = 1; q <= queries; ++q)
        {
            int type = mainScanner.nextInt();
            if(type == 4)
            {
                ArrayList<ArrayList<String>> connectedComponents = gps.calculateConnectedComponents();
                outputHandler.printConnectedComponents(connectedComponents);
                continue;
            }
            Location location1 = readLocationByName(mainScanner, gps);
            Location location2 = readLocationByName(mainScanner, gps);

            switch (type)
            {
                case 1:
                    double distance = gps.findBestRoad(location1, location2);
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

        }


        long endTime = System.currentTimeMillis(), totalTime = endTime - startTime;
//        System.out.println("All queries read");
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Used memory is " + bytesToMegabytes(memory) + " megabytes");
        System.out.println("The program lasted for : " + totalTime / 1000 + " seconds");

        System.gc();
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

    public static long bytesToMegabytes(long bytes) {
        return bytes / (1024 * 1024);
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
30
3
Constanta Pocreaca 1
Constanta Pocreaca 2
Constanta Pocreaca 3


 */