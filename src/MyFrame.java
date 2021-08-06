import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {
    private final TablePanel tablePanel;
    private final CommandsPanel commandsPanel;
    public MyFrame(Controller controller){

        this.setTitle("Poker Game");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1081,875);
        this.getContentPane().setBackground(Color.white);

        tablePanel = new TablePanel();
        commandsPanel = new CommandsPanel(controller,tablePanel);


        this.add(tablePanel,BorderLayout.CENTER);
        this.add(commandsPanel,BorderLayout.SOUTH);
        this.setVisible(true);


    }


    public void setTheBlinds(List<Player> theBlinds){
        commandsPanel.updateTheBlinds(theBlinds);
    }
    public void unfoldThePlayers(){
        tablePanel.unfoldThePlayers();
    }

    public void displayTheWinners(HandCombination bestHandCombination, List<Player> winnersList){

        tablePanel.updateWinnersChips(winnersList);

        JPanel winnersPanel = new JPanel();
        winnersPanel.setLayout(new BorderLayout());
        if (winnersList.size() == 1) winnersPanel.add(new JLabel("The winner is "+winnersList.get(0).getName()),BorderLayout.NORTH);
        else {
            StringBuilder winnersString = new StringBuilder();
            for (Player p : winnersList){
                winnersString.append(p.getName());
                winnersString.append(", ");
            }
            winnersString.deleteCharAt(winnersString.length()-1);
            winnersString.deleteCharAt(winnersString.length()-1);
            winnersPanel.add(new JLabel("The winners are "+ winnersString),BorderLayout.NORTH);
        }
        winnersPanel.add(new HandCombinationPanel(bestHandCombination),BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(null,winnersPanel,"The winners of the turn",JOptionPane.PLAIN_MESSAGE);

    }
    public void ejectLosers(List<Player> losers){
        tablePanel.ejectLosers(losers);
    }
}
