package org.example;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class GraphColor {

    private final List<List<Integer>> graph = new ArrayList<>();

    private int vertices;

    private void buildGraph() {
        vertices = Main.catalog.getDocumentList().size();
        for(int i = 1; i <= vertices + 1; ++i)
            graph.add(new ArrayList<>());
        for(int i = 1;i <= vertices; ++i) {
            for(int j = i + 1;j <= vertices; ++j){

                Document doc1 = Main.catalog.getDocumentById(i),
                         doc2 = Main.catalog.getDocumentById(j);

                if(doc1 == null || doc2 == null)
                    continue;

                List<String> A = doc1.getTagList();
                List<String>  B = doc2.getTagList();

                boolean ok = checkForAdjacency(A, B);
                if(ok) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

            }
        }
    }

    private boolean checkForAdjacency(List<String> A, List<String> B) {
        for(String s : A){
            for(String s2 : B) {
                if(s.equals(s2))
                    return true;
            }
        }
        return false;
    }

    public int execute() {
        buildGraph();
        double x = Math.random();
        if(x > 0.5)
            return backtrackingApproach();
        else
            return greedyApproach();
    }

    private int getMaximumDegree() {
        int best = 0;
        for(int i = 1;i <= vertices; ++i)
            best = Math.max(best, graph.get(i).size());
        return best;
    }

    private int greedyApproach() {

        /// Uses a maximum of c + 1 colors, where c is the maximum degree of a node
        /// It's a greedy approach, doesn't guarantee best result
        /// Graph coloring is an NP Complete problem, so it doesn't have a correct polynomial time solution

        int[] colors = new int[vertices + 1];
        colors[1] = 1;
        int numberOfColors = 1, maxColors = getMaximumDegree();

        for(int i = 2;i <= vertices; ++i) {

            boolean[] usedColors = new boolean[maxColors + 1];
            for(Integer node : graph.get(i)) {
                if(colors[node] != 0)
                    usedColors[colors[node]] = true;
            }
            int goodColor = numberOfColors + 1, bestColor = -1;
            for(int j = 1;j <= numberOfColors; ++j) {
                if(!usedColors[j]) {
                    bestColor = j;
                    break;
                }
            }
            if(bestColor != -1)
                goodColor = bestColor;

            colors[i] = goodColor;
            numberOfColors = Math.max(numberOfColors, colors[i]);

        }
        return numberOfColors;
    }

    private int answer = getMaximumDegree() + 1;

    private int backtrackingApproach() {

        ///Looks into more colorings and chooses the one that uses the least amount of colors.

        int[] color = new int[vertices + 1];
        bkt(1, color);
        return answer;
    }

    private void bkt(int node, int[] color) {
        if(color[node] > answer) {
            return;
        }
        if(node > vertices) {
            boolean[] colors = new boolean[answer + 1];
            int count = 0;
            for(int i = 1;i <= vertices; ++i) {
                if(!colors[i]) {
                    colors[i] = true;
                    count++;
                }
            }
            answer = Math.min(answer, count);
            return;
        }
        for(int c = 1; c <= answer; ++c) {
            boolean ok = true;
            for(Integer neigh : graph.get(node))
            {
                if(color[neigh] == c)
                {
                    ok = false;
                    break;
                }
            }
            if(ok) {
                color[node] = c;
                bkt(node + 1, color);
                color[node] = 0;
            }
        }
    }

}
