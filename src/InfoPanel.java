import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    public InfoPanel(){
        JLabel infoLabel = new JLabel();
        infoLabel.setText("Poker Game Beta Version");
        infoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        this.add(infoLabel);
    }
}
