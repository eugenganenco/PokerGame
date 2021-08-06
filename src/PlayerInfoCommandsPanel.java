import javax.swing.*;
import java.awt.*;

public class PlayerInfoCommandsPanel extends JPanel {
    private final JLabel nameLabel;
    private final JLabel chipsAmountLabel;
    public PlayerInfoCommandsPanel() {
        this.setBackground(Color.GRAY);
        this.setLayout(new GridLayout(2,1));
        nameLabel = new JLabel();
        this.add(nameLabel);
        chipsAmountLabel = new JLabel();
        this.add(chipsAmountLabel);
    }
    public void setName(String name){
        nameLabel.setText("Name: " + name);
    }
    public void setChipsAmount(int chipsAmount){
        chipsAmountLabel.setText("Amount of chips: " + chipsAmount);
    }
}
