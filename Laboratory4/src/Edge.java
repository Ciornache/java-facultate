import java.io.Serializable;
import java.util.Objects;

public class Edge implements Serializable {
    public int x1, y1, x2, y2;
    public int color;
    Edge(int x1, int y1, int x2, int y2, int color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return x1 == edge.x1 && y1 == edge.y1 && x2 == edge.x2 && y2 == edge.y2 && color == edge.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, color);
    }

    
    @Override
    public String toString() {
        return "Edge{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", color=" + color +
                '}';
    }
}
