import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class PlayerPanel extends JPanel {
    private final PlayerInfoPanel playerInfoPanel;
    private final  String name;
    private boolean hasFolded;
    public PlayerPanel(String name){
        hasFolded = false;
        this.name = name;
        Border border = new LineBorder(Color.WHITE, 1, true);
        this.setBorder(border);
        this.setLayout(new BorderLayout());
        ImageIcon playerIcon = new ImageIcon("playerLogo.png");
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(playerIcon);

        playerInfoPanel  = new PlayerInfoPanel(name);


        this.add(iconLabel,BorderLayout.NORTH);
        this.add(playerInfoPanel,BorderLayout.SOUTH);




    }
    public void setChipsAmount(int amount){
        playerInfoPanel.setChipsAmount(amount);
    }
    public void setBigBlindBorder(){
        if (!hasFolded) {
            Border border = new LineBorder(Color.BLUE, 1, true);
            this.setBorder(border);
        }
    }
    public void setSmallBlindBorder(){
        if (!hasFolded) {
            Border border = new LineBorder(Color.RED, 1, true);
            this.setBorder(border);
        }
    }
    public void setCurrentPlayerBorder(){
        Border border = new LineBorder(Color.YELLOW, 1, true);
        this.setBorder(border);
    }
    public void setDefaultBorder(){
        Border border = new LineBorder(Color.WHITE, 1, true);
        this.setBorder(border);
        hasFolded = false;
    }
    public void setFoldedBorder(){
        System.out.println("in folded border");
        hasFolded = true;
        Border border = new LineBorder(Color.DARK_GRAY, 1, true);
        this.setBorder(border);
    }
    public boolean hasFolded(){
        return hasFolded;
    }

    public String getName(){
        return name;
    }

}
