public class pair
{
    public Road road;
    public int node;

    public pair(Road road, int node)
    {
        setRoad(road);
        setNode(node);
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public int getNode() {
        return node;
    }

    @Override
    public String toString() {
        return "pair{" +
                "road=" + road +
                ", node=" + node +
                '}';
    }

    public void setNode(int node) {
        this.node = node;
    }
}