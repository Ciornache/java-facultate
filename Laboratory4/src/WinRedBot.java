import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinRedBot implements Bot {
    private boolean switchMode = false;
    private int mainNode = 0, count = 0;
    private DrawRedBot drawRedBot = null;
    private boolean ok = false;
    private boolean[] usedNode;
    private final List<Integer> winCondition = new ArrayList<>();

    @Override
    public Edge move() {
        if(Objects.isNull(drawRedBot)) {
            return findEdge();
        }
        return drawRedBot.move();
    }

    @Override
    public void strategy() {
        generateNodesPartitions();
        if(!ok)
            drawRedBot = new DrawRedBot();
    }

    public void generateNodesPartitions() {

        int nodes = MainFrame.drawingPanel.numberVertices;
        usedNode = new boolean[nodes + 1];
        bkt(nodes, -1, 0 );
    }

    public void bkt(int nodes, int prevNode, int count) {
        if(count == 5 && !ok) {
            boolean finish = isValidSubgraph(winCondition) >= 9;
            if(!finish)
                return;
            ok = true;
            mainNode = winCondition.get(0);
        }
        for(int node = prevNode + 1; node < nodes && !ok; ++node)
        {
            if(!usedNode[node])
            {
                winCondition.add(node);
                usedNode[node] = true;
                bkt(nodes, node + 1, count + 1);
                usedNode[node] = false;
                winCondition.remove(winCondition.size() - 1);
            }
        }
    }

    public int isValidSubgraph(List<Integer> list)
    {
        int count = 0;
        for(Integer node : list)
        {
            for(Integer node2 : list)
            {
                if(Objects.equals(node, node2))
                    continue;
                Edge currentEdge = MainFrame.drawingPanel.getEdge(node, node2);
                count += (currentEdge != null ? 1 : 0);
            }
        }
        return count;
    }

    private Edge findEdge() {

        if(switchMode)
            return drawRedBot.move();

        List<List<Integer>> graph = MainFrame.drawingPanel.graph;
        int numberVertices = MainFrame.drawingPanel.numberVertices;

        int numberEdg = isValidSubgraph(winCondition);
        if(numberEdg == 9)
        {
            int blue = 0, blueNode1 = -1, blueNode2 = -1;

            boolean[] map = new boolean[numberVertices + 1], map2 = new boolean[numberVertices + 1];

            for(Integer node : winCondition)
            {
                for(Integer node2 : winCondition)
                {
                    if(Objects.equals(node, node2))
                        continue;
                    Edge currEdge = MainFrame.drawingPanel.getEdge(node, node2);
                    if(currEdge == null) {
                        map[node] = true;
                        map[node2] = true;
                        continue;
                    }
                    if(currEdge.getColor() == 2) {
                        blue++;
                        blueNode1 = node;
                        blueNode2 = node2;
                    }
                }
            }
            if(blue == 1)
            {
                for(Integer node : winCondition)
                {
                    if(mainNode == node)
                        continue;
                    Edge currentEdge = MainFrame.drawingPanel.getEdge(mainNode, node);
                    if(currentEdge.getColor() == 1)
                    {
                        map2[mainNode] = true;
                        map2[node] = true;
                        if(checkIncompatibility(blueNode1, blueNode2, mainNode, node, map, map2))
                        {
                            switchMode = true;
                            return drawRedBot.move();
                        }
                    }
                }
            }
        }


        Edge edge = findWin();
        if(edge != null)
            return edge;

        for(Integer node : winCondition)
        {
            Edge currentEdge = MainFrame.drawingPanel.getEdge(mainNode, node);
            if(currentEdge.getColor() == 0)
                return currentEdge;
        }

        return null;

    }

    private Edge findWin() {
        for(Integer node : winCondition)
        {
            for(Integer node2 : winCondition)
            {
                if(Objects.equals(mainNode, node) || Objects.equals(mainNode, node2))
                    continue;
                Edge firstEdge = MainFrame.drawingPanel.getEdge(node, mainNode),
                        secondEdge = MainFrame.drawingPanel.getEdge(node2, mainNode),
                        thirdEdge = MainFrame.drawingPanel.getEdge(node, node2);
                if(firstEdge.getColor() == 1 && secondEdge.getColor() == 1 && thirdEdge.getColor() == 0)
                    return thirdEdge;
            }
        }
        return null;
    }
    private boolean checkIncompatibility(int node, int node2, int node3, int node4, boolean[] a, boolean[] b)
    {
        return (a[node] & b[node3]) | (a[node] & b[node4]) | (a[node2] & b[node3]) | (a[node2] & b[node4]);
    }
}

