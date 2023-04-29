import com.ibm.oti.shared.SharedClassesNamedPermission;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.*;

public class Bonus {

    public static int vertices, edges;
    public static int[][] adjGrid;
    public static int[] permutation;

    private static boolean[] checkNodes;

    private static Vector<Integer> dfsTraversal;
    private static List<List<Integer>> graph;

    public static void solveBonus1() {

        Scanner scaner = new Scanner(System.in);
        vertices = scaner.nextInt();

        adjGrid = new int[vertices + 2][vertices + 2];

        generateCycleGraph(adjGrid);

        int[][] rez = new int[vertices + 2][vertices + 2];
        int power = scaner.nextInt();

        computePower(rez, adjGrid, power);
    }

    public static void solveBonus2() {

        Scanner scanner = new Scanner(System.in);
        vertices = scanner.nextInt();
        edges = scanner.nextInt();

        graph = buildGraph(scanner);

        checkNodes = new boolean[vertices + 2];
        dfsTraversal = new Vector<>(vertices + 2);

        dfs(1);

        for(Integer node : dfsTraversal)
            System.out.print(node + " ");
        System.out.print("\n");
    }

    public static List<List<Integer>> buildGraph(Scanner scanner)
    {

        //TODO : Find out why constructor doesn't work without import.java.util*

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 1;i <= vertices + 2; ++i)
            graph.add(new ArrayList<>());

        adjGrid = new int[vertices + 2][vertices + 2];

        for(int i = 1;i <= edges; ++i)
        {
            int firstNode = scanner.nextInt();
            int secondNode = scanner.nextInt();

            graph.get(firstNode).add(secondNode);
            graph.get(secondNode).add(firstNode);

            adjGrid[firstNode][secondNode] = adjGrid[secondNode][firstNode] = 1;
        }

        return graph;
    }

    public static void dfs(int x)
    {
        checkNodes[x] = true;
        dfsTraversal.add(x);

        for(Integer node : graph.get(x))
        {
            if(!checkNodes[node])
                dfs(node);
        }
    }

    public static void generateCycleGraph(int[][] adjGrid) {
        permutation = new int[vertices + 2];
        for (int i = 1; i <= vertices; ++i)
            permutation[i] = i;

        Homework.shufflePermutation(permutation);

        for (int i = 1; i <= vertices; ++i) {

            int firstNode = permutation[i];
            int secondNode = permutation[i + 1];

            if (i + 1 > vertices)
                secondNode = permutation[1];

            adjGrid[firstNode][secondNode] = 1;
        }
    }

    public static void computePower(int[][] rez, int[][] grid, int power) {

        power %= vertices;

        for (int i = 1; i <= vertices; ++i) {
            for (int j = 1; j <= vertices; ++j)
                rez[i][j] = 0;

            for (int j = 1; j <= vertices; ++j) {
                if (grid[i][j] == 1) {
                    int nextPos = j - power;
                    if (nextPos <= 0)
                        nextPos += vertices;

                    rez[i][nextPos] = 1;
                }
            }
        }
    }

}
