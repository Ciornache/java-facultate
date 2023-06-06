import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
public class LowerPanel extends JPanel implements Serializable {

    private final MainFrame mainFrame;

    List<String> buttonText = List.of(new String[]{"Load", "Save", "Reset", "Exit"});
    LowerPanel(MainFrame mainFrame) {
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
        setColor();
        setSize();
        initializeButtons();
    }

    private void initializeButtons()
    {
        initializeLoadButton();
        initializeSaveButton();
        initializeResetButton();
        initializeExitButton();
    }

    private void initializeResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null) {
                    mainFrame.drawingPanel.resetColors();
                }
            }
        });

        this.add(button);
    }

    private void initializeExitButton() {
        JButton button = new JButton("Exit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null) {
                    System.exit(0);
                }
            }
        });

        this.add(button);
    }

    private void initializeSaveButton() {
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null) {
                    try {
                        mainFrame.drawingPanel.writeObjectToFile(mainFrame);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(button);
    }

    private void initializeLoadButton() {
        JButton button = new JButton("Load");
        JTextArea textArea = new JTextArea("1");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand() != null) {
                    String number = textArea.getText(), file = "Game" + number + ".bin";
                    System.out.println(file);
                    try {
                        mainFrame.drawingPanel.readObjectFromFile(file);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        this.add(button);
        this.add(textArea);
    }

}
