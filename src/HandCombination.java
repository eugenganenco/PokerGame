import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HandCombination {
    private List<Card> combinationCards;
    private int highestCardRank;
    private int rank;
    private HashMap<Integer, Integer> duplicates;

    public HandCombination(List<Card> communityCards, List<Card> playerCards){
        this.combinationCards = new ArrayList<>(communityCards);
        this.combinationCards.addAll(playerCards); // problem here
        highestCardRank = 0;
        rank = 0;
        duplicates = new HashMap<>();
    }

    public HandCombination(){
        highestCardRank = 0;
        rank = 0;
    }


    public void findDuplicates(){
        for (int id = 0; id < 7; id++){
            if (!duplicates.containsKey(combinationCards.get(id).getRank())) {
                duplicates.put(combinationCards.get(id).getRank(), 1);
            }
            else {
                int temp = duplicates.get(combinationCards.get(id).getRank());
                duplicates.remove(combinationCards.get(id).getRank());
                duplicates.put(combinationCards.get(id).getRank(),temp + 1);
            }
        }
    }
    public List<Integer> getRankListWithNoDuplicates(){
        List<Integer> newHand = new ArrayList<>();
        newHand.add(combinationCards.get(0).getRank());
        for (int i = 1; i < 7; i++)
            if (!newHand.contains(combinationCards.get(i).getRank())) newHand.add(combinationCards.get(i).getRank());
        return newHand;
    }

    public boolean betterThan(HandCombination other){
        if (other.getRank() < this.getRank()) return true;
        else if (other.getRank() == this.getRank())
            return other.getHighestCardRank() < this.getHighestCardRank();
        return false;
    }
    public boolean equals(HandCombination other){
        return other.getRank() == this.getRank() && other.getHighestCardRank() == this.getHighestCardRank();
    }
    public void sortHand(){
        combinationCards.sort(new CardComparator());
    }
    public Card getCard(int id){
        return combinationCards.get(id);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getHighestCardRank() {
        return highestCardRank;
    }


    public void setHighestCardRank(int highCardRank) {
        this.highestCardRank = highCardRank;
    }

    public HashMap<Integer, Integer> getDuplicatesHashTable(){
        return duplicates;
    }

    public String toString(){
        return "Rank: " + rank + "; Highest ranking card: " + highestCardRank;
    }

    public String getName(){
        HashMap<Integer,String> name = new HashMap<>();
        name.put(1,"High card");
        name.put(2,"Pair");
        name.put(3,"Two pairs");
        name.put(4,"Three of a kind");
        name.put(5,"Straight");
        name.put(6,"Flush");
        name.put(7,"Full House");
        name.put(8,"Four of a kind");
        name.put(9,"Straight Flush");

        return name.get(this.getRank());
    }

}
