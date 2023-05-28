import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ClassLoadingMXBean;
import java.util.Vector;
import java.util.List;

public class UpperPanel extends JPanel {

    final MainFrame mainFrame;

    public static JComboBox probList;

    public static JSpinner JSpinnerButton;

    UpperPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initializePanel();
    }

    private void setColor() {
        this.setBackground(Color.LIGHT_GRAY);
    }
    private void setSize() {
        this.setPreferredSize(new Dimension(40, 40));
    }

    private void initializePanel() {
//        setColor();
        setSize();
        initializeButtons();

    }

    private void initializeButtons() {
        JSpinnerButton = new JSpinner(new SpinnerNumberModel(10, 3, 100, 1));
        JLabel label1 = new JLabel("Number of dots:");
        this.add(label1);
        this.add(JSpinnerButton);
        initializeProbabilityButton();
        initializeDrawNewGameButton();
    }

    private void initializeDrawNewGameButton() {
        JButton button = new JButton("Create new game");
        button.addActionListener(e -> {
            if(e.getActionCommand() != null) {
                mainFrame.createNewGame();
            }
//            System.out.println("Button pressed");
        });
        this.add(button);
    }

    private void initializeProbabilityButton()
    {
        JLabel label2 = new JLabel("Line probability");
        Double [] probabilities = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.00};
        probList = new JComboBox(probabilities);
        this.add(label2);
        this.add(probList);

    }

}
