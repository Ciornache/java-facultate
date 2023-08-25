import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class UpperPanel extends JPanel implements Serializable {

    final MainFrame mainFrame;

    public JComboBox<Double> probList;

    public JSpinner JSpinnerButton;

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
        initializeBotButton();
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

    private void initializeProbabilityButton() {
        JLabel label2 = new JLabel("Line probability");
        Double [] probabilities = new Double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.00};
        probList = new JComboBox<>(probabilities);
        this.add(label2);
        this.add(probList);

    }

    private void initializeBotButton() {
        JCheckBox botButton = new JCheckBox("Bot");
        botButton.addActionListener(e -> {
            if((int) mainFrame.lowerPanel.JSpinnerButton.getValue() == 2)
                MainFrame.isBotActive = false;
            else
                MainFrame.isBotActive = botButton.isSelected();

        });
        this.add(botButton);
    }

}
