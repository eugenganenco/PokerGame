import javax.swing.*;
import java.awt.*;

public class GuidingPanel extends JPanel {
    public GuidingPanel(){
        this.setLayout(new GridLayout(2,3));
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(100,60));
        JLabel smallBlindLabel = new JLabel("SB");
        JLabel bigBlindLabel = new JLabel("BB");
        JLabel currentPlayerLabel = new JLabel("CP");

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setPreferredSize(new Dimension(20,20));
        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setPreferredSize(new Dimension(20,20));
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.yellow);
        yellowPanel.setPreferredSize(new Dimension(20,20));

        this.add(smallBlindLabel);
        this.add(bigBlindLabel);
        this.add(currentPlayerLabel);

        this.add(redPanel);
        this.add(bluePanel);
        this.add(yellowPanel);


    }

}
