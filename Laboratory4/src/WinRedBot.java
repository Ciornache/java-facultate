import javax.swing.*;
import java.util.ArrayList;
import java.util.Currency;
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
//        System.out.println("MOVING");
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

    WinRedBot() {
        strategy();
    }

    public void generateNodesPartitions() {
        int nodes = MainFrame.drawingPanel.numberVertices;
        usedNode = new boolean[nodes + 1];
        bkt(nodes, -1, 0 );
/*
        System.out.println(ok);
*/
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
                bkt(nodes, node, count + 1);
                if(ok)
                    break;
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
        return count / 2;
    }

    private Edge findEdge() {

        if(switchMode)
            return drawRedBot.move();

//        System.out.println(winCondition + " " + isValidSubgraph(winCondition));

        List<List<Integer>> graph = MainFrame.drawingPanel.graph;
        int numberVertices = MainFrame.drawingPanel.numberVertices;

        int numberEdg = isValidSubgraph(winCondition);

        for(Integer node : winCondition)
        {
            if(Objects.equals(mainNode, node))
                continue;
            Edge currentEdge = MainFrame.drawingPanel.getEdge(node, mainNode);
            if(currentEdge == null)
                continue;
            if(currentEdge.getColor() == 1)
            {
                for(Integer node2 : winCondition)
                {
                    if(Objects.equals(mainNode, node2) || Objects.equals(node2, node))
                        continue;
                    Edge oppositeEdge = MainFrame.drawingPanel.getEdge(node2, node);
                    if(oppositeEdge == null)
                        continue;

//                    System.out.println("LOL" + " " + oppositeEdge.getColor() + " " +  node + " " + node2);
                    if(oppositeEdge.getColor() == 2 && count == 0)
                    {
                        mainNode = node;
                        count = 1;
                        break;
                    }
                }
            }
            if(count == 1)
                break;
        }

        int missingNode1 = -1, missingNode2 = -1;

        if(numberEdg == 9)
        {
            int blue = 0, blueNode1 = -1, blueNode2 = -1;

            boolean[] map = new boolean[numberVertices + 2], map2 = new boolean[numberVertices + 2];

            for(Integer node : winCondition)
            {
                for(Integer node2 : winCondition)
                {
                    if(Objects.equals(node, node2))
                        continue;
                    Edge currEdge = MainFrame.drawingPanel.getEdge(node, node2);
                    if(currEdge == null) {
//                        System.out.println(node + " " + node2);
                        missingNode1 = node;
                        missingNode2 = node2;
                        continue;
                    }
                    if(currEdge.getColor() == 2) {
                        blue++;
                        blueNode1 = node;
                        blueNode2 = node2;
                    }
                }
            }

            if(mainNode == missingNode1 || mainNode == missingNode2) {
                for (Integer node : winCondition) {
                    if(node != missingNode1 && node != missingNode2) {
                        mainNode = node;
                        break;
                    }
                }
            }

            blue /= 2;
//            System.out.println(blue + " BLUE EDGES");

            if(blue == 1)
            {
                if(checkIncompatibility(blueNode1, blueNode2, missingNode1, missingNode2))
                {
//                    System.out.println("SWITCHED");
                    switchMode = true;
                    drawRedBot = new DrawRedBot();
                    return drawRedBot.move();
                }
            }
            else if(blue == 0 || blue == 2)
            {
                for(Integer node : winCondition)
                {
                    if(Objects.equals(node, mainNode) || Objects.equals(node, missingNode2) || Objects.equals(node, missingNode1))
                        continue;
                    Edge currEdge = MainFrame.drawingPanel.getEdge(mainNode, node);
                    if(currEdge.getColor() == 0)
                        return currEdge;
                }
            }
        }


        Edge edge = findWin();
        if(edge != null)
            return edge;

        System.out.println(mainNode);

        for(Integer node : winCondition)
        {
            Edge currentEdge = MainFrame.drawingPanel.getEdge(mainNode, node);
            if(Objects.equals(mainNode, node))
                continue;
            if(currentEdge == null)
                continue;
            if(currentEdge.getColor() == 0)
                return currentEdge;
        }

        return drawRedBot.move();

    }

    private Edge findWin() {
        for(Integer node : winCondition)
        {
            for(Integer node2 : winCondition)
            {
                if(Objects.equals(mainNode, node) || Objects.equals(mainNode, node2))
                    continue;
                if(Objects.equals(node, node2))
                    continue;

                Edge firstEdge = MainFrame.drawingPanel.getEdge(node, mainNode),
                        secondEdge = MainFrame.drawingPanel.getEdge(node2, mainNode),
                        thirdEdge = MainFrame.drawingPanel.getEdge(node, node2);

                if(firstEdge == null || secondEdge == null || thirdEdge == null)
                    continue;

                if(firstEdge.getColor() == 1 && secondEdge.getColor() == 1 && thirdEdge.getColor() == 0)
                    return thirdEdge;
            }
        }
        if(drawRedBot == null)
            drawRedBot = new DrawRedBot();
        return null;
    }
    private boolean checkIncompatibility(int node, int node2, int node3, int node4) {
        return node != node3 && node != node4 && node2 != node3 && node2 != node4;
    }
}

