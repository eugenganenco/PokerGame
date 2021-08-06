import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.lang.Thread.*;


public class Player {
    private String name;
    private Integer chips;
    private List<Card> hand;
    private PokerTable pokerTable;
    private boolean hasFolded;
    private int lastBetAmount;

    private boolean hasActed;




    public Player(String name, int chips, PokerTable pokerTable){
        this.pokerTable = pokerTable;
        this.name = name;
        this.chips = chips;
        this.hand = new ArrayList<>();
        this.lastBetAmount = 0;

        hasFolded = false;
        hasActed = false;
    }
    public void receiveCard(Card card){
        hand.add(card);
        System.out.println(name + " got a/an " + card);
    }
    public void call(){
        new Call(this,pokerTable);
    }
    public void bet(int amount){
        new Bet(this,pokerTable,amount);
    }

    public void emptyHand(){
        hand.clear();
    }
    public int getChips(){
        return chips;
    }
    public List<Card> getHand(){
        return hand;
    }
    public String getHandString(){
        if (hand.isEmpty()) return " Empty ";
        return new String(hand.get(0).toString()) + " " +  new String(hand.get(1).toString());
    }


    public String toString(){
        return name;
    }
    public void takeChips(int amount){
        chips = chips - amount;
    }
    public void awardChips(int amount){ chips = chips + amount; }
    public boolean hasFolded(){
        return hasFolded;
    }
    public void fold(){
        hasFolded = true;
        pokerTable.incrementPlayersFolded();
    }
    public void unFold(){
        hasFolded = false;
    }
    public String getName(){return name;}
    public void setHasActed(boolean state){ hasActed = state;}
    public void setLastBetAmount(int lastBetAmount) {
        this.lastBetAmount = lastBetAmount;
    }
    public int getLastBetAmount() {
        return this.lastBetAmount ;
    }

}
