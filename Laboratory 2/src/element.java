public class element {

    int node;

    int cost;

    public element(int node, int cost){
        setCost(cost);
        setNode(node);
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "element{" +
                "node=" + node +
                ", cost=" + cost +
                '}';
    }
}
