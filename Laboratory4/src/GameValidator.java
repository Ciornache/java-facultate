import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameValidator implements Serializable {

    private final DrawingPanel drawingPanel = MainFrame.drawingPanel;

    public boolean isGood() {
        boolean ok = hardMode();
//        boolean ok = easyMode();
        return !ok;
    }

    private boolean easyMode() {
        return checkForCycles();
    }

    private boolean hardMode() {
        boolean ok1 = checkForCycles();
        boolean ok2 = checkForIntersections();
        boolean ok3 = checkSecondTypeIntersection();
//        System.out.println(ok1 + " " + ok2 + " " + ok3);
        return ok1 | ok2 | ok3;
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
                    while(cop != min && cop != max)
                    {
                        rightPart.add(cop);
                        cop++;
                        cop %= numberVertices;
                    }
                    cop = i - 1;
                    if(cop < 0)
                        cop = numberVertices - 1;
                    while(cop != min && cop != max)
                    {
                        leftPart.add(cop);
                        cop--;
                        if(cop < 0)
                            cop = numberVertices - 1;
                    }
                    for (Integer integer : rightPart) {
                        for (Integer value : leftPart) {
                            int x = integer, y = value;
                            Edge currentEdge = drawingPanel.getEdge(x, y);
                            if (currentEdge == null)
                                continue;
                            Edge firstEdge = drawingPanel.getEdge(node1, i),
                                    secondEdge = drawingPanel.getEdge(node2, i);
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
    private boolean checkSecondTypeIntersection() {
        List<List<Integer>> graph = drawingPanel.graph;
        int numberVertices = drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            for(Integer node : graph.get(i))
            {
                if(i == node)
                    continue;
                for(Integer node2 : graph.get(node))
                {
                    if(Objects.equals(node2, node))
                        continue;
                    if(node2 == i)
                        continue;
                    List<Integer> blockedNodes = new ArrayList<>();
                    int mx = Math.max(i, node), mn = Math.min(i, node);
                    if(i == mx) {
                        mx++;
                        if (mx == numberVertices)
                            mx = 0;
                        while (mx != mn) {
                            blockedNodes.add(mx);
                            mx++;
                            if (mx == numberVertices)
                                mx = 0;
                        }
                    }
                    else
                    {
                        mn--;
                        if(mn < 0)
                            mn = numberVertices - 1;
                        while(mx != mn)
                        {
                            blockedNodes.add(mn);
                            mn--;
                            if(mn < 0)
                                mn = numberVertices - 1;
                        }
                    }
                    boolean ok = false;
                    for(Integer node3 : blockedNodes)
                    {
                        if(Objects.equals(node2, node3)) {
                            ok = true;
                            break;
                        }
                    }
                    if(ok)
                    {
                        List<Integer> blNodes = new ArrayList<>();
                        for(int j = 0;j < numberVertices; ++j)
                        {
                            boolean ok2 = true;
                            for(Integer node3 : blockedNodes)
                            {
                                if(node3 == j) {
                                    ok2 = false;
                                    break;
                                }
                            }
                            if(ok2) {
                                blNodes.add(j);
                            }
                        }
                        blockedNodes = blNodes;
                    }
//                    System.out.println(i + " " + node + " " + node2);
//                    for(Integer node3 : blockedNodes)
//                        System.out.print(node3 + " ");
//                    System.out.println();
                    for(Integer node3 : graph.get(node2))
                    {

                        if(Objects.equals(node3, node2))
                            continue;
                        if(node3 == i)
                            continue;
                        if(Objects.equals(node3, node))
                            continue;
                        ok = false;
                        for(Integer node4 : blockedNodes)
                            ok |= (node3.equals(node4));
                        if(ok) {
                            Edge currentEdge = drawingPanel.getEdge(node, i);
                            Edge firstEdge = drawingPanel.getEdge(node2, node),
                                    secondEdge = drawingPanel.getEdge(node3, node2);
                            if(currentEdge == null || firstEdge == null || secondEdge == null)
                                continue;
                            int product = currentEdge.getColor() * firstEdge.getColor() * secondEdge.getColor();
                            if(product == 1 || product == 8)
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
