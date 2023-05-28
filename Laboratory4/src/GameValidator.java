import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameValidator {

    private final DrawingPanel drawingPanel = MainFrame.drawingPanel;

    public boolean isGood()
    {
        boolean ok1 = checkForCycles();
        boolean ok2 = checkForIntersections();
        boolean ok3 = checkSecondTypeIntersection();
        boolean ok = ok1 | ok2 | ok3;
        return !ok;
    }

    private boolean checkForCycles()
    {
        List<List<Integer>> graph = drawingPanel.graph;
        int numberVertices = drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            List<Integer> adjNodes = new ArrayList<>(graph.get(i));
            for(Integer node : adjNodes)
            {
                for(Integer node2 : adjNodes)
                {
                    if(Objects.equals(node, node2))
                        continue;
                    Edge currentEdge = drawingPanel.getEdge(node, node2);
                    Edge firstEdge = drawingPanel.getEdge(i, node),
                            secondEdge = drawingPanel.getEdge(i, node2);
                    if(currentEdge == null || firstEdge == null || secondEdge == null)
                        continue;
                    int product = currentEdge.getColor() * firstEdge.getColor() * secondEdge.getColor();
                    if(product == 1 || product == 8)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean checkForIntersections()
    {
        List<List<Integer>> graph = drawingPanel.graph;
        int numberVertices = drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            List<Integer> adjNodes = new ArrayList<>(graph.get(i));
            for(Integer node1 : adjNodes)
            {
                for(Integer node2 : adjNodes)
                {
                    if(Objects.equals(node1, node2))
                        continue;
                    List<Integer> leftPart = new ArrayList<>(),rightPart = new ArrayList<>();
                    int cop = i + 1, min = Math.min(node1, node2), max = Math.max(node1, node2);
                    cop %= numberVertices;
                    while(cop > min)
                    {
                        leftPart.add(cop);
                        cop++;
                        cop %= numberVertices;
                    }
                    while(cop < min)
                    {
                        leftPart.add(cop);
                        cop++;
                        cop %= numberVertices;
                    }
                    cop = i - 1;
                    if(cop < 0) cop = numberVertices - 1;
                    while(cop < max)
                    {
                        rightPart.add(cop);
                        cop--;
                        if(cop < 0)
                            cop = numberVertices - 1;
                    }
                    while(cop > max)
                    {
                        rightPart.add(cop);
                        cop--;
                    }
                    for (Integer integer : rightPart) {
                        for (Integer value : leftPart) {
                            int x = integer, y = value;
                            Edge currentEdge = drawingPanel.getEdge(x, y);
                            if (currentEdge == null)
                                continue;
                            Edge firstEdge = drawingPanel.getEdge(x, i),
                                    secondEdge = drawingPanel.getEdge(y, i);
                            if (firstEdge == null || secondEdge == null)
                                continue;
                            int product = currentEdge.getColor() * firstEdge.getColor() * secondEdge.getColor();
                            if (product == 1 || product == 8)
                                return true;
                        }
                    }
                }
            }
        }
        return false;

    }
    private boolean checkSecondTypeIntersection()
    {
        List<List<Integer>> graph = drawingPanel.graph;
        int numberVertices = drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            for(Integer node : graph.get(i))
            {
                for(Integer node2 : graph.get(node))
                {
                    int curr = node - 1;
                    if(curr < 0) curr = numberVertices;
                    while(curr != node)
                    {
                        for(Integer node3 : graph.get(node2))
                        {
                            if(node3 == curr)
                            {
                                Edge currentEdge = drawingPanel.getEdge(node3, node2);
                                Edge firstEdge = drawingPanel.getEdge(i, node),
                                        secondEdge = drawingPanel.getEdge(node, node2);
                                if(currentEdge == null || firstEdge == null || secondEdge == null)
                                    continue;
                                int product = currentEdge.getColor() * firstEdge.getColor() * secondEdge.getColor();
                                if(product == 1 || product == 8)
                                    return true;
                            }
                        }
                        curr--;
                        if(curr < 0)
                            curr = numberVertices;
                    }
                }
            }
        }
        return false;
    }
}
