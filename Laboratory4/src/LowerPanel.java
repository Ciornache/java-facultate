import javax.swing.*;
import java.awt.*;
import java.util.List;
public class LowerPanel extends JPanel {

/////
    List<String> buttonText = List.of(new String[]{"Load", "Save", "Reset", "Exit"});
    LowerPanel() {
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

        for(String str : buttonText)
        {
            JButton button = new JButton(str);
            this.add(button);
        }
    }

}
