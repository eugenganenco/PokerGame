import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel{

    public final JButton callButton;
    public final JButton foldButton;
    public final JButton betButton;
    public final JTextField amountToBetTextField;

    public ButtonsPanel(CommandsPanel commandsPanel){

        this.setLayout(new GridLayout(2,2));
        this.setBackground(Color.GRAY);
        callButton = new JButton();
        callButton.setText("Call");
        callButton.addActionListener(commandsPanel);
        callButton.setFocusable(false);
        this.add(callButton);

        foldButton = new JButton();
        foldButton.setText("Fold");
        foldButton.addActionListener(commandsPanel);
        foldButton.setFocusable(false);
        this.add(foldButton);

        betButton = new JButton();
        betButton.setText("Bet");
        betButton.addActionListener(commandsPanel);
        betButton.setFocusable(false);
        this.add(betButton);

        amountToBetTextField = new JTextField();
        amountToBetTextField.setText("Set amount to bet");
        this.add(amountToBetTextField);
        this.revalidate();

    }

    public JButton getFoldButton(){
        return foldButton;
    }
    public JButton getCallButton(){
        return callButton;
    }
    public JButton getBetButton(){
        return betButton;
    }
    public JTextField getAmountToBetTextField(){
        return amountToBetTextField;
    }
    public void setTextFieldNull(){amountToBetTextField.setText("");}





}
