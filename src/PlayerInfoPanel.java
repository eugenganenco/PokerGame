import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {
    private JLabel chipsLabel;
    private JLabel nameLabel;

    public PlayerInfoPanel(String name){
        this.setLayout(new BorderLayout());
        chipsLabel = new JLabel();
        nameLabel = new JLabel();
        chipsLabel.setText("1000");
        nameLabel.setText(name);
        this.add(chipsLabel,BorderLayout.SOUTH);
        this.add(nameLabel,BorderLayout.NORTH);
    }
    public void setChipsAmount(int newAmount){
        System.out.println("new amount" + newAmount);
        System.out.println("new amount" + newAmount);
        System.out.println("new amount" + newAmount);
        chipsLabel.setText(String.valueOf(newAmount));
    }
}
