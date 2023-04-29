import java.util.*;

public class Waze implements Gps {

    private final int INF = 1000000000;

    private static HashMap<String, Integer> hashMap;

    InputReader inputReader;
    List<List<pair>> graph;
    int [] dp, parentNode;
    public Waze() {

        inputReader = new InputReader();

        hashMap = InputReader.getHashMap();
        this.graph = InputReader.getGraph();

        initializeUtils();
    }

    @Override
    public int findBestRoad(Location x, Location y) {

        bfs(x, y);

        int finish = getHashCode(y);
        if(dp[finish] == INF)
            return -1;
        else
            return dp[finish];
    }

    @Override
    public List<Road> getBestRoad(Location x, Location y) {
        bfs(x, y);
        int finish = getHashCode(y);
        int start = getHashCode(x);
        return backtrack(start, finish);
    }

    private List<Road> backtrack(int start, int finish){

        List<Road> answer = new ArrayList<>();
        while(parentNode[finish] != 0)
        {
            Road road = findSingleRoad(parentNode[finish], finish);
            finish = parentNode[finish];
            answer.add(road);
        }

        if(finish != start)
            return null;

        int size = answer.size();

        for(int i = 0;i < size / 2; ++i)
        {
            Road aux = answer.get(i);
            answer.set(i, answer.get(size - i - 1));
            answer.set(size - i - 1, aux);
        }

        return answer;
    }

    private Road findSingleRoad(int start, int finish)
    {
        for(pair p : graph.get(start))
        {
            if(p.getNode() == finish)
                return p.getRoad();
        }
        return null;
    }

    @Override
    public boolean isRoad(Location x, Location y) {
        bfs(x, y);
        int finish = getHashCode(y);
        return dp[finish] != -1;
    }

    private void resetUtils() {
        int n = inputReader.getNumberOfLocations();
        for(int i = 1;i <= n; ++i) {
            dp[i] = INF;
            parentNode[i] = 0;
        }
    }

    private void initializeUtils()
    {
        int n = inputReader.getNumberOfLocations();
        dp = new int [n + 2];
        parentNode = new int [n + 2];
    }

    private void bfs(Location x, Location y) {

        resetUtils();

        int startNode = getHashCode(x);
        dp[startNode] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);

        while(!q.isEmpty())
        {
            Integer currNode = q.peek();
            q.remove();
            for(pair p : graph.get(currNode))
            {
                int cost = p.getRoad().getLength();
                int adjNode = p.getNode();
                if(dp[adjNode] > dp[currNode] + cost)
                {
                    dp[adjNode] = dp[currNode] + cost;
                    parentNode[adjNode] = currNode;
                    q.add(currNode);
                }
            }
        }
    }



    public static String findTypeOfLocation(Location x)
    {
        _location[] arr = _location.values();
//        return _location.valueOf(x.getType().name()).name();
        for(_location loc : arr)
        {
            if(loc.ordinal() == x.getType().ordinal())
                return loc.name();
        }

        return null;
    }

    public static Integer getHashCode(Location x){
        return hashMap.get(x.getName());
    }

    public static String getNameForLocation(int codedLocation)
    {
        for(Location loc : InputReader.locations)
        {
            Integer code = getHashCode(loc);
            if(code == codedLocation)
                return loc.getName();
        }
        return null;
    }

    public Location getLocationForName(String name)
    {
        for(Location loc : InputReader.locations)
        {
            if(Objects.equals(loc.getName(), name))
                return loc;
        }
        return null;
    }

}
