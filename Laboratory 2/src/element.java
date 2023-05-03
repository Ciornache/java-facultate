public class element {

    int node;

    double cost;

    public element(int node, double cost){
        setCost(cost);
        setNode(node);
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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
