import java.util.*;

public class Waze implements Gps {

    int limitSpeed;
    private final int INF = 1000000000;

    private static HashMap<String, Integer> hashMap;

    Stack<Integer> st;

    InputReader inputReader;
    ArrayList<ArrayList<String>> connectedComponents;
    List<List<pair>> graph, revGraph;
    boolean [] check;
    int [] parentNode;
    double [] dp;
    public Waze() {

        inputReader = new InputReader();

        hashMap = inputReader.getHashMap();
        this.graph = inputReader.getGraph();
        this.revGraph = inputReader.getRevGraph();

        initializeUtils();
        limitSpeed = InputReader.getCar().getSpeed();
    }

    @Override
    public double findBestRoad(Location x, Location y) {

        dijkstra(x);
        int finish = getHashCode(y);
        if(dp[finish] == INF)
            return -1;
        else
            return dp[finish];
    }

    @Override
    public List<pair> getBestRoad(Location x, Location y) {
        dijkstra(x);
        int finish = getHashCode(y);
        int start = getHashCode(x);
        return backtrack(start, finish);
    }

    private List<pair> backtrack(int start, int finish){

        List<pair> answer = new ArrayList<>();
        while(parentNode[finish] != 0)
        {
            Road road = findSingleRoad(parentNode[finish], finish);

            finish = parentNode[finish];
            answer.add(new pair(road, finish));

        }

        if(finish != start)
            return null;

        int size = answer.size();

        for(int i = 0;i < size / 2; ++i)
        {
            pair aux = answer.get(i);
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
        dijkstra(x);
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
        dp = new double [n + 2];
        parentNode = new int [n + 2];
        connectedComponents = new ArrayList<>();
        check = new boolean[n + 2];
        st = new Stack<>();
    }

    private void bfs(Location x) {

        resetUtils();

        int startNode = getHashCode(x);
        dp[startNode] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(startNode);

        while(!q.isEmpty())
        {
            Integer currNode = q.peek();
            System.out.println(currNode);
            q.remove();
            for(pair p : graph.get(currNode))
            {
                int cost = p.getRoad().getLength();
                int adjNode = p.getNode();
                if(dp[adjNode] > dp[currNode] + cost && limitSpeed <= p.getRoad().getSpeedLimit())
                {
                    dp[adjNode] = dp[currNode] + cost;
                    parentNode[adjNode] = currNode;
                    q.add(adjNode);
                }
            }
        }
    }

    private void dijkstra(Location x)
    {

        resetUtils();

        int startNode = getHashCode(x);
        dp[startNode] = 0;

        PriorityQueue<element> pq = new PriorityQueue<>((o1, o2) -> {

            double cost1 = o1.getCost(), cost2 = o2.getCost();

            int node1 = o1.getNode(), node2 = o2.getNode();

            if (cost1 > cost2)
                return -1;
            else if (cost2 > cost1)
                return 1;
            else {
                if (node1 > node2)
                    return -1;
                else if (node2 > node1)
                    return 1;
            }

            return 0;
        });

        pq.add(new element(startNode, dp[startNode]));

        while(!pq.isEmpty())
        {
            element el = pq.peek();
            pq.remove();

            int node = el.getNode();
            double cost = el.getCost();

            if(dp[node] != cost)
                continue;

            for(pair p : graph.get(node))
            {
                int nextNode = p.getNode(), pathCost = p.getRoad().getLength();
                if(dp[nextNode] > dp[node] + pathCost / (double)limitSpeed && limitSpeed <= p.getRoad().getSpeedLimit())
                {
                    dp[nextNode] = dp[node] + pathCost / (double)limitSpeed;
                    parentNode[nextNode] = node;
                    pq.add(new element(nextNode, dp[nextNode]));
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

    public void printLocations() {
        List<Location> locations = inputReader.getLocations();
        for(Location location : locations)
            System.out.println(location);
        System.out.println();
    }

    public void printRoads()
    {
        List<List<pair>> graph = inputReader.getGraph();
        int nodes = inputReader.getNumberOfLocations();
        for(int i = 1; i <= nodes; ++i)
        {
            for(pair p : graph.get(i))
                if(p.getNode() >= i)
                    System.out.println(i + " " + p);
        }
        System.out.println();
    }

    private void dfs(Integer x)
    {
        check[x] = true;
        for(pair p : graph.get(x))
        {
            Integer nextNode = p.getNode();
            if(!check[nextNode])
                dfs(nextNode);
        }
        st.add(x);
    }

    private void dfs2(Integer x, int index)
    {
        check[x] = true;
        connectedComponents.get(index).add(getNameForLocation(x));
        for(pair p : revGraph.get(x))
        {
            Integer nextNode = p.getNode();
            if(!check[nextNode])
                dfs2(nextNode, index);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> calculateConnectedComponents() {

        if(connectedComponents.size() != 0)
            return connectedComponents;

        Arrays.fill(check, false);

        connectedComponents.add(new ArrayList<>());

        for(int i = 1; i <= inputReader.numberOfLocations; ++i)
        {
            if(!check[i])
            {
                connectedComponents.add(new ArrayList<>());
                dfs(i);
            }
        }

        Arrays.fill(check, false);

        int count = 0;
        while(!st.isEmpty())
        {
            int node = st.peek();
            if(!check[node])
            {
                count++;
                dfs2(node, count);
            }
            st.pop();
        }

        return connectedComponents;
    }
}
