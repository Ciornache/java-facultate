import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public static List<List<pair>> getGraph() {
        return graph;
    }

    public static void setGraph(List<List<pair>> graph) {
        InputReader.graph = graph;
    }

    private static List<List<pair>> graph;

    private final static HashMap<String, Integer> hashMap = new HashMap<>();

    public static HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public int numberOfLocations, numberOfRoads;
    public static List<Location> locations;

    public static Scanner scanner;

    public InputReader() {
        readInput();
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public int getNumberOfRoads() {
        return numberOfRoads;
    }

    public void setNumberOfRoads(int numberOfRoads) {
        this.numberOfRoads = numberOfRoads;
    }

    public void readInput()
    {
        initializeScanner();
        readLocations();
        readRoads();
    }

    private void initializeScanner(){
        scanner = new Scanner(System.in);
    }

    private void readLocations()  {

        numberOfLocations = scanner.nextInt();
        locations = new ArrayList<>();

        for(int i = 1;i <= numberOfLocations; ++i)
        {
            Location currentLocation = readLocation(scanner);

            boolean valid = checkLocation(currentLocation);
            if(!valid)
            {
                System.out.println("Already given location");
                continue;
            }

            String name = currentLocation.getName();
            hashMap.put(name, hashMap.size() + 1);

            locations.add(currentLocation);
        }

    }
    private boolean checkLocation(Location currentLocation)
    {
        for(Location location : locations)
        {
            if(currentLocation.equals(location))
                return false;
        }
        return true;
    }


    private Location readLocation(Scanner scanner) {
        return new Location(readLocationType(scanner), readName(scanner), readPosition(scanner));
    }

    public String readName(Scanner scanner) {
        return scanner.next();
    }

    public position readPosition(Scanner scanner){
        position thisPos = new position();
        thisPos.x = scanner.nextInt();
        thisPos.y = scanner.nextInt();
        return thisPos;
    }
    private void readRoads() {

        graph = new ArrayList<>();

        for(int i = 1;i <= numberOfLocations + 2; ++i)
            graph.add(new ArrayList<>());

        numberOfRoads = scanner.nextInt();
        for(int i = 1;i <= numberOfRoads; ++i)
        {
            String  firstLocation = scanner.next(),
                    secondLocation = scanner.next();

            if(hashMap.get(firstLocation) == null || hashMap.get(secondLocation) == null)
            {
                System.out.println("Unrecognized locations");
                continue;
            }

            int firstHashCode = hashMap.get(firstLocation), secondHashCode = hashMap.get(secondLocation);
            Road road = readRoad();

            graph.get(firstHashCode).add(new pair(road, secondHashCode));
            graph.get(secondHashCode).add(new pair(road, firstHashCode));
        }
    }
    public _location readLocationType(Scanner scanner){

        _location[] arr = _location.values();
        int type = scanner.nextInt();
        for(_location loc : arr)
        {
            if(loc.ordinal() == type)
                return loc;
        }
        return null;
    }

    public _road readRoadType()
    {
        _road[] arr = _road.values();
        int type = scanner.nextInt();
        for(_road ro : arr)
        {
            if(ro.ordinal() == type)
                return ro;
        }
        return null;
    }

    private Road readRoad() {
        int length = scanner.nextInt();
        int speedLimit = scanner.nextInt();
        _road roadType = readRoadType();
        return new Road(length, speedLimit, roadType);
    }
}
