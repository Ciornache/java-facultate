import java.util.*;
import java.util.concurrent.Callable;


public class DrawRedBot implements Bot {


    @Override
    public Edge move() {
        return findNextEdge();
    }

    @Override
    public void strategy() {

    }

    private Edge findNextEdge() {
//        System.out.println("HERE");
        Edge blueWin = searchForBlueWin();
        if(blueWin != null)
            return blueWin;

        List<List<Integer>> graph = MainFrame.drawingPanel.graph;
        int numberVertices = MainFrame.drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            List<Integer> adjNodes = new ArrayList<>(graph.get(i));
            for(Integer node : adjNodes)
            {
                for(Integer node2 : adjNodes)
                {
                    if(Objects.equals(node, node2))
                        continue;
                    Edge currentEdge = MainFrame.drawingPanel.getEdge(node, node2);
                    Edge firstEdge = MainFrame.drawingPanel.getEdge(i, node),
                            secondEdge = MainFrame.drawingPanel.getEdge(i, node2);
                    if(currentEdge == null || firstEdge == null || secondEdge == null)
                        continue;
//                    System.out.println("VALID EDGES");
                    boolean ok = ((currentEdge.getColor() != 2) & (firstEdge.getColor() != 2) & (secondEdge.getColor() != 2));
                    if(ok)
                    {
//                        System.out.println("GOT THE EDGE");
                        if(currentEdge.getColor() == 0)
                            return currentEdge;
                        if(firstEdge.getColor() == 0)
                            return firstEdge;
                        if(secondEdge.getColor() == 0)
                            return secondEdge;
                    }
                }
            }
        }

        for(int i = 0;i < numberVertices; ++i)
        {
            for(Integer node : graph.get(i))
            {
                Edge currentEdge = MainFrame.drawingPanel.getEdge(i, node);
                if(currentEdge != null && currentEdge.getColor() == 0)
                    return currentEdge;
            }
        }


        return null;
    }

    public Edge searchForBlueWin() {
        List<List<Integer>> graph = MainFrame.drawingPanel.graph;
        int numberVertices = MainFrame.drawingPanel.numberVertices;
        for(int i = 0;i < numberVertices; ++i)
        {
            List<Integer> adjNodes = new ArrayList<>(graph.get(i));
            for(Integer node : adjNodes)
            {
                for(Integer node2 : adjNodes)
                {
                    if(Objects.equals(node, node2))
                        continue;
                    Edge currentEdge = MainFrame.drawingPanel.getEdge(node, node2);
                    Edge firstEdge = MainFrame.drawingPanel.getEdge(i, node),
                            secondEdge = MainFrame.drawingPanel.getEdge(i, node2);
                    if(currentEdge == null || firstEdge == null || secondEdge == null)
                        continue;
                    boolean ok = ((currentEdge.getColor() != 1) & (firstEdge.getColor() != 1) & (secondEdge.getColor() != 1));
                    if(ok)
                    {
                        int ok2 = (currentEdge.getColor() != 0 ? 1 : 0) + (firstEdge.getColor() != 0 ? 1 : 0) + (secondEdge.getColor() != 0 ? 1 : 0);
                        if(ok2 == 1)
                        {
                            if(currentEdge.getColor() == 0)
                                return currentEdge;
                            if(firstEdge.getColor() == 0)
                                return firstEdge;
                            if(secondEdge.getColor() == 0)
                                return secondEdge;
                        }
                    }
                }
            }
        }
        return null;
    }
}
