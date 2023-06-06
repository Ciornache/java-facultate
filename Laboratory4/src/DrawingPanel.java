import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrawingPanel extends JPanel implements Serializable{

    private MainFrame mainFrame;

    final boolean[] turn = {true};

    private final List<Edge> coloredEdges = new ArrayList<>();

    protected volatile boolean isDeleted = false;

    public List<List<Integer>> graph;

    GameValidator gameValidator;

    private int firstDotX, firstDotY;
    private int secondDotX, secondDotY;

    DrawingPanel getInstance() {
        return this;
    }

    public List<Edge> edges;

    final private int circleSize = 20;
    final private int W = 800;
    final private int H = 600;

    public List<Integer> x, y, pointColors;
    public int numberVertices;

    private void initializeDrawingPanel() {
        setColor();
        createVertices();
        createEdges();

    }

    DrawingPanel(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(W, H));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int point = findPointByCoordinates(e.getX(), e.getY());
                if (point != -1 && pointColors.get(point) == 0) {
                    pointColors.set(point, 1);
                    if (firstDotY == -1) {
                        firstDotY = y.get(point);
                        firstDotX = x.get(point);
                    } else {
                        pointColors.set(point, 0);
                        secondDotY = y.get(point);
                        secondDotX = x.get(point);
                        point = findPointByCoordinates(firstDotX + 10, firstDotY + 10);
                        pointColors.set(point, 0);
                    }
                    SwingUtilities.invokeLater(() -> repaint());
                }
                else if(point != -1 && pointColors.get(point) != 0) {
                    firstDotX = firstDotY = -1;
                    pointColors.set(point, 0);
                    SwingUtilities.invokeLater(() -> repaint());
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        initializeDrawingPanel();
    }

    private void setColor() {
        this.setBackground(Color.WHITE);
    }

    private void createVertices() {
        try {
        numberVertices = (int) mainFrame.upperPanel.JSpinnerButton.getValue();
        }
        catch (NullPointerException e) {
            numberVertices = 10;
            e.getStackTrace();
        }
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numberVertices; // the angle
        x = new ArrayList<>();
        y = new ArrayList<>();
        pointColors = new ArrayList<>();
        for (int i = 0; i < numberVertices; i++) {
            x.add(x0 + (int) (radius * Math.cos(alpha * i)));
            y.add(y0 + (int) (radius * Math.sin(alpha * i)));
            pointColors.add(0);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;
        drawEdges(graphics2);
        drawVertices(graphics2);
    }

    private void drawVertices(Graphics2D g) {
        System.out.println("Drawing Vertices");
        numberVertices = (int) mainFrame.upperPanel.JSpinnerButton.getValue();
        g.setColor(Color.BLACK);
//        g.setStroke(new BasicStroke(10));
        System.out.println("Paint Component Method Called");
        for (int i = 0; i < numberVertices; ++i) {
            if(pointColors.get(i) == 0)
                g.setColor(Color.BLACK);
            else
                g.setColor(Color.YELLOW);
            g.fillOval(x.get(i), y.get(i), circleSize, circleSize);
        }
    }

    private void drawEdges(Graphics2D g) {
//        System.out.println(numberVertices);
        g.setStroke(new BasicStroke(2));
        for (Edge currentEdge : coloredEdges) {
            int color = currentEdge.getColor();
            if (color == 0)
                g.setColor(Color.GRAY);
            else if (color == 1)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLUE);
            g.drawLine(currentEdge.getX1() + circleSize / 2, currentEdge.getY1() + circleSize / 2,
                    currentEdge.getX2() + circleSize / 2, currentEdge.getY2() + circleSize / 2);
        }
    }

    public int findPointByCoordinates(int xCoord, int yCoord) {
        for (int i = 0; i < numberVertices; ++i) {
            int centerXCoord = x.get(i) + circleSize / 2, centerYCoord = y.get(i) + circleSize / 2;
            double distance = Math.sqrt((centerXCoord - xCoord) * (centerXCoord - xCoord) +
                    (centerYCoord - yCoord) * (centerYCoord - yCoord));
            if (distance <= (double) circleSize / 2)
                return i;
        }
        return -1;
    }

    private void createEdges() {
        graph = new ArrayList<>();
        for (int i = 1; i <= numberVertices; ++i)
            graph.add(new ArrayList<>());
        double probability = 1.00;
        try {
            probability = (double) mainFrame.upperPanel.probList.getSelectedItem();
        }
        catch(NullPointerException e) {
            System.out.println("Null Pointer Exception Caught");
            e.getStackTrace();
        }
        edges = new ArrayList<>();
        for (int i = 0; i < numberVertices; ++i) {
            for (int j = 0; j < numberVertices; ++j) {
                if (i == j)
                    continue;
                int leftFav = 0, rightFav = (int) (1000 * probability);
                int rnd = (int) (Math.random() * 1000);
//                System.out.println(leftFav + " " + rightFav + " " + rnd);
                if (rnd >= leftFav && rnd <= rightFav && i < x.size() && j < y.size()) {
                    edges.add(new Edge(x.get(i), y.get(i), x.get(j), y.get(j), 0));
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                    coloredEdges.add(new Edge(x.get(i), y.get(i), x.get(j), y.get(j), 0));
                }
            }
        }
    }

    public synchronized boolean setEdgeColor(int x1, int y1, int x2, int y2, int color) {
        for (Edge currentEdge : edges) {
            if (currentEdge.getX1() == x1 && currentEdge.getY1() == y1 && currentEdge.getX2() == x2 && currentEdge.getY2() == y2) {
                if (currentEdge.getColor() != 0)
                    return false;

                for(Edge edge : coloredEdges) {
                    if(edge.equals(currentEdge))
                    {
                        coloredEdges.remove(edge);
                        break;
                    }
                }

                coloredEdges.add(new Edge(x1, y1, x2, y2, color));
                getParent().repaint();
                repaint();
                return true;
            }
            if (currentEdge.getX1() == x2 && currentEdge.getY1() == y2 && currentEdge.getX2() == x1 && currentEdge.getY2() == y1) {
                if (currentEdge.getColor() != 0)
                    return false;

                for(Edge edge : coloredEdges) {
                    if(edge.equals(currentEdge))
                    {
                        coloredEdges.remove(edge);
                        break;
                    }
                }

                coloredEdges.add(new Edge(x1, y1, x2, y2, color));
                getParent().repaint();
                repaint();
                return true;
            }
        }
        return false;
    }

    public void runGame() {
        gameValidator = new GameValidator();
        firstDotX = -1;
        firstDotY = -1;
        secondDotX = -1;
        secondDotY = -1;
        Timer gameTimer = new Timer(100, e -> {

            if (!gameValidator.isGood()) {
                if (turn[0])
                    System.out.println("BLUE WON");
                else
                    System.out.println("RED WON");
                ((Timer) e.getSource()).stop();
            }

            if (firstDotX != -1 && secondDotX != -1) {
                int color = 1;
                if (!turn[0])
                    color++;
                boolean ok = setEdgeColor(firstDotX, firstDotY, secondDotX, secondDotY, color);
                if (ok) {
                    turn[0] = !turn[0];
                    repaint();
                }
                firstDotX = -1;
                firstDotY = -1;
                secondDotX = -1;
                secondDotY = -1;
            }
            if(isDeleted) {
                ((Timer) e.getSource()).stop();
            }
        });
        gameTimer.start();
    }

    public Edge getEdge(int node1, int node2) {
        int x1 = x.get(node1), y1 = y.get(node1);
        int x2 = x.get(node2), y2 = y.get(node2);
        Edge ans = null;
        int bestColor = -1;
        for (Edge currentEdge : coloredEdges) {
            if (currentEdge.getX1() == x1 && currentEdge.getX2() == x2 && currentEdge.getY1() == y1 && currentEdge.getY2() == y2) {
                if(currentEdge.getColor() > bestColor) {
                    bestColor = currentEdge.getColor();
                    ans = currentEdge;
                }
            }
            if (currentEdge.getX1() == x2 && currentEdge.getX2() == x1 && currentEdge.getY1() == y2 && currentEdge.getY2() == y1) {
                if(currentEdge.getColor() > bestColor) {
                    bestColor = currentEdge.getColor();
                    ans = currentEdge;
                }
            }
        }
        return ans;
    }


    public void resetColors() {
        for (Edge edge : edges) edge.setColor(0);
        coloredEdges.clear();
        coloredEdges.addAll(edges);
        turn[0] = true;
        this.repaint();
    }

    protected void writeObjectToFile(MainFrame mainFrame) throws IOException {

        Scanner scanner = new Scanner(new File("D:\\Documents\\java-facultate\\Laboratory4\\src\\GameCounter.txt"));
        String fileIndex = scanner.next();
        scanner.close();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Game" + fileIndex + ".bin"));
        objectOutputStream.writeObject(this);

        int nextFile = Integer.parseInt(fileIndex);
        nextFile++;
        fileIndex = Integer.toString(nextFile);

        FileWriter fileWriter = new FileWriter("D:\\Documents\\java-facultate\\Laboratory4\\src\\GameCounter.txt");
        fileWriter.write(fileIndex);
        fileWriter.close();

    }

    public void readObjectFromFile(String fileName) throws IOException, ClassNotFoundException {
        System.out.println(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        DrawingPanel currentDrawingPanel = (DrawingPanel) objectInputStream.readObject();
        updateDrawingPanel(currentDrawingPanel);
    }

    public void updateDrawingPanel(DrawingPanel newDrawingPanel) {
        // Update the fields one by one
        this.mainFrame = newDrawingPanel.mainFrame;
        this.turn[0] = newDrawingPanel.turn[0];
        this.coloredEdges.clear();
        this.coloredEdges.addAll(newDrawingPanel.coloredEdges);
        this.isDeleted = newDrawingPanel.isDeleted;
        this.graph = new ArrayList<>(newDrawingPanel.graph);
        this.gameValidator = newDrawingPanel.gameValidator;
        this.firstDotX = newDrawingPanel.firstDotX;
        this.firstDotY = newDrawingPanel.firstDotY;
        this.secondDotX = newDrawingPanel.secondDotX;
        this.secondDotY = newDrawingPanel.secondDotY;
        this.x = new ArrayList<>(newDrawingPanel.x);
        this.y = new ArrayList<>(newDrawingPanel.y);
        this.edges = new ArrayList<>(newDrawingPanel.edges);
        this.pointColors = new ArrayList<>(newDrawingPanel.pointColors);
        this.numberVertices = newDrawingPanel.numberVertices;
        // Update other fields as needed

        // Call any initialization methods again if required
        mainFrame.repaint();
        super.repaint();
        this.repaint();
    }


}
