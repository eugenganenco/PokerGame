import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HandCombinationPanel extends JPanel {
    public HandCombinationPanel(HandCombination handCombination){
        this.setBorder(new LineBorder(Color.DARK_GRAY,2,true));
        this.setLayout(new BorderLayout());

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout());
        for (int i = 0; i < 7; i++){
            cardsPanel.add(new JLabel(new ImageIcon(handCombination.getCard(i).getImageFileName())));
        }

        this.add(cardsPanel,BorderLayout.NORTH);

        JLabel handCombinationLabel = new JLabel(handCombination.getName());
        this.add(handCombinationLabel,BorderLayout.SOUTH);
    }
}
