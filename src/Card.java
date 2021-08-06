import javax.swing.*;
import java.util.HashMap;

public class Card {
    private int id;
    private int rank;
    private String value;
    private String suit;
    private String imageFileName;

    public Card(int id){
        this.id = id;
        HashMap<Integer, String> possibleSuits = new HashMap<>();
        HashMap<Integer, String> possibleValues = new HashMap<>();

        possibleSuits.put(0, "C");
        possibleSuits.put(1, "D");
        possibleSuits.put(2, "H");
        possibleSuits.put(3, "S");

        possibleValues.put(0,"2");
        possibleValues.put(1,"3");
        possibleValues.put(2,"4");
        possibleValues.put(3,"5");
        possibleValues.put(4,"6");
        possibleValues.put(5,"7");
        possibleValues.put(6,"8");
        possibleValues.put(7,"9");
        possibleValues.put(8,"10");
        possibleValues.put(9,"J");
        possibleValues.put(10,"Q");
        possibleValues.put(11,"K");
        possibleValues.put(12,"A");

        this.suit = possibleSuits.get(Math.floorDiv(id, 13));
        this.rank = Math.floorMod(id,13);
        this.value = possibleValues.get(rank);
    }

    public int getRank(){
        return rank;
    }
    public String getSuit(){ return suit; }
    public String getImageFileName(){return value + suit + ".png";}
    public String toString() {
        return value + " of " + suit + " ("+ id + ")";
    }
}
