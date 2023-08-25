import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {

    public  UpperPanel upperPanel;
    public  LowerPanel lowerPanel;

    public static DrawingPanel drawingPanel;
    public MainFrame()
    {
        super("My Application");
        setColor();
        initializeLayout();
        createPanels();
        this.pack();
        drawingPanel.runGame();
    }

    private void createPanels()
    {
        upperPanel = new UpperPanel(this);
        lowerPanel = new LowerPanel(this);
        drawingPanel = new DrawingPanel(this);
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(lowerPanel, BorderLayout.SOUTH);
        this.add(drawingPanel, BorderLayout.CENTER);
    }

    private void initializeLayout() {
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }

    private void setColor(){
        this.setBackground(Color.WHITE);
    }

    public void createNewGame()
    {
        drawingPanel.isDeleted = true;
        this.remove(drawingPanel);
        drawingPanel = null;
        System.gc();
        drawingPanel = new DrawingPanel(this);
        add(drawingPanel);
        this.pack();
        drawingPanel.runGame();
    }



}
