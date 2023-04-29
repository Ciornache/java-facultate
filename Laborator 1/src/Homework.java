import java.util.Scanner;
import java.lang.Math;

public class Homework {

    public static int squareSize;

    public static String[] stringfiedRows, stringfiedColumns;

    public static void solveHomework()  {

        long startTime = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        readInput(scanner);
        if(squareSize < 1 || squareSize > 1e6)
        {
            System.out.print("INVALID INPUT");
            return;
        }
        int[][] grid = new int[squareSize + 2][squareSize + 2];
        createLatinSquare(grid);
        stringifyColumns(grid);
        stringifyRows(grid);
//        printGrid(grid);
        printStringifiedGrid();
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000000000 + " seconds");
    }

    private static void createLatinSquare(int [][] grid) {

        int[] identicalPermutation = new int[squareSize + 2];
        for(int i = 1;i <= squareSize; ++i)
            identicalPermutation[i] = i;

        shufflePermutation(identicalPermutation);

        for(int i = 1;i <= squareSize; ++i)
        {
            for(int j = 1;j <= squareSize; ++j)
            {
                int nextIndex = (j + i - 1) % squareSize;
                if(nextIndex == 0)
                    nextIndex = squareSize;
                grid[i][j] = identicalPermutation[nextIndex];
            }
        }

    }

    public static void shufflePermutation(int[] permutation) {
        for(int i = 1; i <= squareSize; ++i)
        {
            int firstIndex = (int)(Math.random() * squareSize);
            firstIndex = Math.max(firstIndex, 1);

            int secondIndex = (int)(Math.random() * squareSize);
            secondIndex = Math.max(secondIndex, 1);
            swap(permutation, firstIndex, secondIndex);
        }
    }

    private static void swap(int[] array, int a, int b)
    {
        int aux = array[b];
        array[b] = array[a];
        array[a] = aux;
    }

    private static void readInput(Scanner scanner) {
        squareSize = scanner.nextInt();
    }

    private static void printGrid(int [][] grid) {

        for(int i = 1;i <= squareSize; ++i)
        {
            for(int j = 1;j <= squareSize; ++j) {
                System.out.print(grid[i][j]);
                System.out.print(' ');
            }
            System.out.print("\n");
        }
    }

    private static void stringifyRows(int [][] grid) {

        stringfiedRows = new String[squareSize * 9 + 2];
        for(int i = 1;i <= squareSize; ++i)
        {
            for(int j = 1;j <= squareSize; ++j)
                    stringfiedRows[i] += String.valueOf(grid[i][j]);

        }
    }

    private static void stringifyColumns(int [][] grid)
    {
        stringfiedColumns = new String[squareSize * 9 + 2];
        for(int i = 1;i <= squareSize; ++i) {
            for(int j = 1;j <= squareSize; ++j)
                stringfiedColumns[i] += String.valueOf(grid[j][i]);
        }
    }

    private static void printStringifiedGrid()
    {
        for(int i = 1;i <= squareSize; ++i)
            System.out.print(stringfiedRows[i].substring(4) + "\n");
        for(int i = 1;i <= squareSize; ++i)
            System.out.print(stringfiedColumns[i].substring(4) + "\n");
    }

}
