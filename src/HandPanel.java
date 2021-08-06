import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HandPanel extends JPanel {
    private final JLabel card1;
    private final JLabel card2;
    public HandPanel(){
        this.setBackground(Color.GRAY);
        this.setLayout(new FlowLayout());
        card1 = new JLabel();
        card2 = new JLabel();
        this.add(card1);
        this.add(card2);
    }
    public void updateHandPanel(List<Card> hand){
        ImageIcon card1Icon = new ImageIcon(hand.get(0).getImageFileName());
        ImageIcon card2Icon = new ImageIcon(hand.get(1).getImageFileName());
        card1.setIcon(card1Icon);
        card2.setIcon(card2Icon);
    }
}
