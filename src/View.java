import javax.swing.*;
import java.util.List;

public class View {
    Controller controller;
    MyFrame myFrame;
    public View(Controller controller){
        this.controller = controller;
        myFrame = new MyFrame(controller);
    }

    public void setTheBlinds(List<Player> theBlinds){
        myFrame.setTheBlinds(theBlinds);
    }
    public void unfoldThePlayers(){
        myFrame.unfoldThePlayers();
    }
    public void displayTheWinners(HandCombination bestHandCombination, List<Player> winnersList){
        myFrame.displayTheWinners(bestHandCombination,winnersList);
    }
    public void ejectLosers(List<Player> losersList){
        System.out.println("Losers list sent to the View"+ losersList);
        myFrame.ejectLosers(losersList);
    }
    public void displayTheWinnerOfTheGame(Player player){ JOptionPane.showMessageDialog(null,player.getName() + " has won the game");}


}
