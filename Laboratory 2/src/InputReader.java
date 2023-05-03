import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    static public Car getCar(){
        return car;
    }

    static private Car car;

    public void setLocations(List<Location> locations) {
        InputReader.locations = locations;
    }

    public List<List<pair>> getRevGraph() {
        return revGraph;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputVerifier getInputVerifier() {
        return inputVerifier;
    }

    public void setInputVerifier(InputVerifier inputVerifier) {
        this.inputVerifier = inputVerifier;
    }

    public List<Location> getLocations() {
        return locations;
    }

    InputVerifier inputVerifier;

    public List<List<pair>> getGraph() {
        return graph;
    }

    public static void setGraph(List<List<pair>> graph) {
        InputReader.graph = graph;
    }

    private static List<List<pair>> graph, revGraph;

    private final static HashMap<String, Integer> hashMap = new HashMap<>();

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public int numberOfLocations, numberOfRoads;
    public static List<Location> locations;

    public  Scanner scanner;

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
        initializeVerifier();
        readLocations();
        readRoads();
        car = readCar();
    }

    private void initializeScanner(){
        scanner = new Scanner(System.in);
    }

    private void initializeVerifier() {inputVerifier = new InputVerifier();}

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

    private void readLocations()  {

        numberOfLocations = scanner.nextInt();
        locations = new ArrayList<>();

        for(int i = 1;i <= numberOfLocations; ++i)
        {
            Location currentLocation = readLocation(scanner);

            boolean valid = inputVerifier.isAlreadyLocation(currentLocation, locations);

            if(!valid)
            {
                System.out.println("Already given location");
                i--;
                continue;
            }

            String name = currentLocation.getName();
            hashMap.put(name, hashMap.size() + 1);

            locations.add(currentLocation);
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

    private void readRoads() {

        graph = new ArrayList<>();
        revGraph = new ArrayList<>();
        for(int i = 1;i <= numberOfLocations + 2; ++i) {
            graph.add(new ArrayList<>());
            revGraph.add(new ArrayList<>());
        }
        numberOfRoads = scanner.nextInt();
        for(int i = 1;i <= numberOfRoads; ++i)
        {
            String  firstLocation = scanner.next(),
                    secondLocation = scanner.next();

            if(firstLocation.equals(secondLocation))
            {
                System.out.println("Cannot add an edge between the same locations");
                i--;
                continue;
            }

            boolean valid = inputVerifier.checkLocation(firstLocation, hashMap)
                            & inputVerifier.checkLocation(secondLocation, hashMap);

            if(!valid) {
                System.out.println("The edges doesn't match the already existing location");
                i--;
                continue;
            }

            int firstHashCode = hashMap.get(firstLocation), secondHashCode = hashMap.get(secondLocation);
            Road road = readRoad();

            valid = inputVerifier.verifyDistance(firstLocation, secondLocation, locations,
                    road.getLength());

            if(!valid)
            {
                System.out.println("The length is greater than the distance between the two locations");
                i--;
                continue;
            }

            valid = inputVerifier.isEdgeIn(firstLocation, secondLocation, graph, hashMap);

            if(valid)
            {
                System.out.println("The edge is already part of the graph");
                i--;
                continue;
            }
            graph.get(firstHashCode).add(new pair(road, secondHashCode));
            revGraph.get(secondHashCode).add(new pair(road, firstHashCode));
            if(!road.getType().equals(_road.ONE_WAY_STREET)) {
                graph.get(secondHashCode).add(new pair(road, firstHashCode));
                revGraph.get(firstHashCode).add(new pair(road, secondHashCode));
            }
        }
    }

    private Car readCar()
    {
        int speed = scanner.nextInt();
        return new Car.Builder().buildBrand("Cupra").buildWheels(4).buildEngine(2).buildSpeed(speed).buildCar();
    }

}
