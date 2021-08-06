import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;


public class HandRankingAlgorithm {

    public HandCombination execute(Player player, List<Card> communityCards){
        System.out.println(" ");
        System.out.println("Community cards before constructor:"+communityCards);
        HandCombination handCombination = new HandCombination(communityCards,player.getHand());
        System.out.println("Community cards after constructor:" + communityCards);
        System.out.println(" ");

        if (player.hasFolded()){
            handCombination.setRank(0);
            handCombination.setHighestCardRank(0);
            return handCombination;

        }

        handCombination.sortHand();
        handCombination.findDuplicates();



        if (isStraightFlush(handCombination)) {
            handCombination.setRank(9);
            return handCombination;
        }

        if (isFourOfAKind(handCombination)){
            handCombination.setRank(8);
            return handCombination;
        }

        if (isFullHouse(handCombination)){
            handCombination.setRank(7);
            return handCombination;
        }
        if (isFlush(handCombination)){
            handCombination.setRank(6);
            return handCombination;
        }

        if (isStraight(handCombination)){
            handCombination.setRank(5);
            return handCombination;
        }

        if (isThreeOfAKind(handCombination)){
            handCombination.setRank(4);
            return handCombination;
        }

        if (isTwoPairs(handCombination)){
            handCombination.setRank(3);
            return handCombination;
        }

        if (isPair(handCombination)){
            handCombination.setRank(2);
            return handCombination;
        }

        isHighCard(handCombination);
        handCombination.setRank(1);
        return handCombination;

    }

    private boolean isStraightFlush(HandCombination handCombination){
        return isFlush(handCombination) && isStraight(handCombination);
    }



    private boolean isFourOfAKind(HandCombination handCombination){

        HashMap<Integer, Integer> duplicates = handCombination.getDuplicatesHashTable();

        for (Integer rank : duplicates.keySet()) {
            if (duplicates.get(rank) == 4) {
                handCombination.setHighestCardRank(rank);
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(HandCombination handCombination){
        boolean pairFlag = false;
        boolean threeOfAKindFlag = false;
        HashMap<Integer, Integer> duplicates = handCombination.getDuplicatesHashTable();
        for (Integer rank : duplicates.keySet()){
            if (duplicates.get(rank) == 2) pairFlag = true;
            if (duplicates.get(rank) == 3) {
                threeOfAKindFlag = true;
                handCombination.setHighestCardRank(rank);
            }
        }
        return pairFlag && threeOfAKindFlag;
    }

    private boolean isFlush(HandCombination handCombination){
        int spades = 0;
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;


        for (int i = 0; i < 7; i++){
            if (handCombination.getCard(i).getSuit().equals("C")) clubs++;
            if (handCombination.getCard(i).getSuit().equals("D")) diamonds++;
            if (handCombination.getCard(i).getSuit().equals("H")) hearts++;
            if (handCombination.getCard(i).getSuit().equals("S")) spades++;
        }
        if (spades == 5){
            flushHelper(handCombination,"S");
            return true;
        }
        if (clubs == 5){
            flushHelper(handCombination,"C");
            return true;
        }
        if (diamonds == 5){
            flushHelper(handCombination,"D");
            return true;
        }
        if (hearts == 5){
            flushHelper(handCombination,"H");
            return true;
        }

        return false;
    }

    private void flushHelper(HandCombination handCombination,String suit){ // weird function, might cause trouble
        int highestRank = 0;
        for (int i = 0; i < 7; i++)
            if (handCombination.getCard(i).getRank() > highestRank)
                highestRank = handCombination.getCard(i).getRank();
        handCombination.setHighestCardRank(highestRank);
    }

    private boolean isStraight(HandCombination handCombination){   // I think can be simplified, but should not cause trouble

        List<Integer> newHand = handCombination.getRankListWithNoDuplicates();
        newHand.sort(new IntegerComparator());
        int consecutiveRanks = 0;
        int p1 = 0;
        int p2 = 1;

        while (p2 < newHand.size() && consecutiveRanks != 4){
            if (newHand.get(p2) - newHand.get(p1) == 1) {
                consecutiveRanks++;

            }
            else consecutiveRanks = 0;
            p1++;
            p2++;
        }

        if (consecutiveRanks == 4){
            handCombination.setHighestCardRank(newHand.get(p2 - 1));
            return true;
        }
        return false;

    }

    private boolean isThreeOfAKind(HandCombination handCombination){
        HashMap<Integer, Integer> duplicates = handCombination.getDuplicatesHashTable();
        List<Integer> triplets = new ArrayList<>();
        for (Integer rank : duplicates.keySet()){
            if (duplicates.get(rank) == 3){
                triplets.add(rank);
            }
        }
        int maxRank = 0;
        if (triplets.size() > 0) {
            for (Integer triplet : triplets) if (triplet > maxRank) maxRank = triplet;
            handCombination.setHighestCardRank(maxRank);
            return true;
        }
        return false;
    }

    private boolean isTwoPairs(HandCombination handCombination){
        HashMap<Integer, Integer> duplicates = handCombination.getDuplicatesHashTable();
        List<Integer> pairs = new ArrayList<>();
        for (Integer rank : duplicates.keySet()){
            if (duplicates.get(rank) == 2){
                pairs.add(rank);
            }
        }
        int maxRank = 0;
        if (pairs.size() > 1) {
            for (Integer triplet : pairs) if (triplet > maxRank) maxRank = triplet;
            handCombination.setHighestCardRank(maxRank);
            return true;
        }
        return false;
    }


    private boolean isPair(HandCombination handCombination){
        HashMap<Integer, Integer> duplicates = handCombination.getDuplicatesHashTable();

        for (Integer rank : duplicates.keySet()){
            if (duplicates.get(rank) == 2){
                handCombination.setHighestCardRank(rank);
                return true;
            }
        }
        return false;

    }

    private void isHighCard(HandCombination handCombination){
        int maxRank = 0;
        for (int i = 0; i < 7; i++){
            if (handCombination.getCard(i).getRank() > maxRank) maxRank = handCombination.getCard(i).getRank();
        }
        handCombination.setHighestCardRank(maxRank);

    }


}
