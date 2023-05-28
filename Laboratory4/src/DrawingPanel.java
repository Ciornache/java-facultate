import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel implements MouseListener {

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

    public List<Integer> x, y;
    public int numberVertices;

    private void initializeDrawingPanel() {
        setColor();
        createVertices();
        createEdges();

    }

    DrawingPanel() {
        this.setPreferredSize(new Dimension(W, H));
        initializeDrawingPanel();
    }

    private void setColor() {
        this.setBackground(Color.WHITE);
    }

    private void createVertices() {
        numberVertices = (int) UpperPanel.JSpinnerButton.getValue();
        int x0 = W / 2;
        int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numberVertices; // the angle
        x = new ArrayList<>();
        y = new ArrayList<>();
        for (int i = 0; i < numberVertices; i++) {
            x.add(x0 + (int) (radius * Math.cos(alpha * i)));
            y.add(y0 + (int) (radius * Math.sin(alpha * i)));
        }
        for (int i = 0; i < numberVertices; ++i)
            System.out.println(x.get(i) + " " + y.get(i));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;
//      graphics.drawLine(100, 100, 200, 200);
        drawEdges(graphics2);
        drawVertices(graphics2);
    }

    private void drawVertices(Graphics2D g) {
        numberVertices = (int) UpperPanel.JSpinnerButton.getValue();
        g.setColor(Color.BLACK);
//        g.setStroke(new BasicStroke(10));
        System.out.println("Paint Component Method Called");
        for (int i = 0; i < numberVertices; ++i) {
            g.setColor(Color.BLACK);
            g.fillOval(x.get(i), y.get(i), circleSize, circleSize);
//            g.setColor(Color.RED);
//            g.drawOval(x.get(i), y.get(i), 1, 1);
        }
    }

    private void drawEdges(Graphics2D g) {
//        System.out.println(numberVertices);
        g.setPaintMode();
        g.setStroke(new BasicStroke(2));
        for (Edge currentEdge : edges) {
            int color = currentEdge.getColor();
            if (color == 0)
                g.setColor(Color.GRAY);
            else if (color == 1)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLUE);
            System.out.println(currentEdge.getColor());
            g.drawLine(currentEdge.getX1() + circleSize / 2, currentEdge.getY1() + circleSize / 2,
                    currentEdge.getX2() + circleSize / 2, currentEdge.getY2() + circleSize / 2);
        }
//        g.setColor(Color.BLACK);
//        for(int i = 0;i < numberVertices; ++i)
//            g.fillOval(x[i], y[i], 20, 20);
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

        double probability = (double) UpperPanel.probList.getSelectedItem();
        edges = new ArrayList<>();
        for (int i = 0; i < numberVertices; ++i) {
            for (int j = 0; j < numberVertices; ++j) {
                if (i == j)
                    continue;
                int leftFav = 0, rightFav = (int) (1000 * probability);
                int rnd = (int) (Math.random() * 1000);
                int diameter = circleSize;
                if (rnd >= leftFav && rnd <= rightFav && i < x.size() && j < y.size()) {
                    edges.add(new Edge(x.get(i), y.get(i), x.get(j), y.get(j), 0));
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
    }

    public synchronized boolean setEdgeColor(int x1, int y1, int x2, int y2, int color) {
        for (Edge currentEdge : edges) {
            if (currentEdge.getX1() == x1 && currentEdge.getY1() == y1 && currentEdge.getX2() == x2 && currentEdge.getY2() == y2) {
                if (currentEdge.getColor() != 0)
                    return false;
                currentEdge.setColor(color);
                System.out.println("AM SETAT CULOAREA " + color);
                System.out.println(currentEdge);
                repaint();
                updateUI();
                revalidate();
                return true;
            }
            if (currentEdge.getX1() == x2 && currentEdge.getY1() == y2 && currentEdge.getX2() == x1 && currentEdge.getY2() == y1) {
                if (currentEdge.getColor() != 0)
                    return false;
                currentEdge.setColor(color);
                System.out.println("AM SETAT CULOAREA " + color);
                System.out.println(currentEdge);
                repaint();
                updateUI();
                revalidate();
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int point = findPointByCoordinates(e.getX(), e.getY());
        System.out.println(point);
        if (point != -1) {
            if (firstDotY == -1) {
                firstDotY = y.get(point);
                firstDotX = x.get(point);
            } else {
                secondDotY = y.get(point);
                secondDotX = x.get(point);
            }
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

    public void runGame() {
        addMouseListener(this);
        gameValidator = new GameValidator();
        final boolean[] turn = {true};
        firstDotX = -1;
        firstDotY = -1;
        secondDotX = -1;
        secondDotY = -1;
        Timer gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            }
        });
        gameTimer.start();
    }

    public Edge getEdge(int node1, int node2) {
        int x1 = x.get(node1), y1 = y.get(node1);
        int x2 = x.get(node2), y2 = y.get(node2);
        for (Edge currentEdge : edges) {
            if (currentEdge.getX1() == x1 && currentEdge.getX2() == x2 && currentEdge.getY1() == y1 && currentEdge.getY2() == y2)
                return currentEdge;
            if (currentEdge.getX1() == x2 && currentEdge.getX2() == x1 && currentEdge.getY1() == y2 && currentEdge.getY2() == y1)
                return currentEdge;
        }
        return null;
    }

}
