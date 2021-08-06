import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class PokerTable {
    private int pot;
    private int amountToCall;
    private int playersFolded;
    private List<Card> communityCards;
    private StackOfCards deck;
    private PlayersLinkedList playersList;
    private HandCombination bestHandCombination;


    private Player firstToActPointer;
    private HandRankingAlgorithm handRankingAlgorithm;



    public PokerTable(){
        playersList = new PlayersLinkedList();
        communityCards = new ArrayList();
        amountToCall = 50;
        deck = new StackOfCards();
        firstToActPointer = null;
        bestHandCombination = null;
        handRankingAlgorithm = new HandRankingAlgorithm();


    }

    public void addPlayer(Player player){
        playersList.addPlayer(player);
    }
    public boolean onePLayerLeft(){
        System.out.println("SIze: " + playersList.size());
        return playersList.size() == 1;
    }
    public Player getTheRemainingPlayer(){
        if (playersList.size() == 1)
            return playersList.getCurrentPlayer();
        return null;
    }
    public void emptyHands(){
        playersList.setCurrentPlayerTail();
        playersList.getCurrentPlayer().emptyHand();
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            playersList.getCurrentPlayer().emptyHand();
            playersList.next();
        }

    }
    public void nextPlayer(){
        playersList.next();
    }
    public void shuffleCards(){
        deck = new StackOfCards();
    }
    public List<Player> getEjectedLosers(){
        List<Player> losers = new ArrayList<>();
        playersList.setCurrentPlayerTail();
        System.out.println(playersList.getCurrentPlayer().getName() + " has: " +playersList.getCurrentPlayer().getChips());
        if (playersList.getCurrentPlayer().getChips() < 1) {
            losers.add(playersList.getCurrentPlayer());
            playersList.ejectCurrentPlayer();

        }
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){

            if (playersList.getCurrentPlayer().getChips() < 1){
                System.out.println(playersList.getCurrentPlayer().getName() + " has: " +playersList.getCurrentPlayer().getChips());
                losers.add(playersList.getCurrentPlayer());
                playersList.ejectCurrentPlayer();
            }
            else playersList.next();
        }
        return losers;
    }
    public void distributeCards(){
        playersList.setCurrentPlayerTail();
        playersList.getCurrentPlayer().receiveCard(deck.takeACard());
        playersList.getCurrentPlayer().receiveCard(deck.takeACard());
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            playersList.getCurrentPlayer().receiveCard(deck.takeACard());
            playersList.getCurrentPlayer().receiveCard(deck.takeACard());
            playersList.next();
        }
    }

    public void setRandomSmallBlind(){
        playersList.setRandomSmallBlind();
    }
    public void setNewSmallBlind(){playersList.setNewSmallBlind();}

    public void displayPLayers(){
        playersList.setCurrentPlayerTail();
        System.out.println(playersList.getCurrentPlayer());
        playersList.next();
        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            System.out.println(playersList.getCurrentPlayer());
            playersList.next();
        }
    }
    public void setPlayerToActPreFlop(){
            playersList.setCurrentPlayerUTG();
            firstToActPointer = playersList.getCurrentPlayer();
    }
    public void setPlayerToActFlop(){
        playersList.setCurrentPlayerSB();
        firstToActPointer = playersList.getCurrentPlayer();
    }

    public void unfoldEveryone(){
        playersFolded = 0;
        playersList.setCurrentPlayerTail();
        playersList.getCurrentPlayer().unFold();
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            playersList.getCurrentPlayer().unFold();
            playersList.next();
        }
    }

    public boolean everyoneFolded(){
        return playersList.size() - 1 == playersFolded;
    }

    public List<Player> findTheWinnersOfTheTurn(){
        HashMap<Player,HandCombination> contenders = new HashMap<>();
        List<Player> winnersList;
        playersList.setCurrentPlayerTail();
        HandCombination hand = handRankingAlgorithm.execute
                (playersList.getCurrentPlayer(),communityCards);
        contenders.put(playersList.getCurrentPlayer(),hand);
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            hand = handRankingAlgorithm.execute
                    (playersList.getCurrentPlayer(),communityCards);


            contenders.put(playersList.getCurrentPlayer(),hand);
            playersList.next();
        }

        System.out.println(contenders);
        bestHandCombination = findTheBestCombination(contenders);
        winnersList = getWinnersList(contenders,bestHandCombination);
        return winnersList;

    }
    private List<Player> getWinnersList(HashMap<Player,HandCombination> contenders,HandCombination bestCombination){
        List<Player> winnersList = new LinkedList<>();
        for (Player p : contenders.keySet())
            if (contenders.get(p).equals(bestCombination)) winnersList.add(p);
        return winnersList;
    }

    private HandCombination findTheBestCombination(HashMap<Player,HandCombination> contenders){
        HandCombination bestSoFar = new HandCombination();


        for (Player p : contenders.keySet())
            if (contenders.get(p).betterThan(bestSoFar)) bestSoFar = contenders.get(p);
        return bestSoFar;
    }

    public void awardTheWinners(List<Player> winnersList){
        int award = pot / winnersList.size();
        System.out.println(winnersList);
        for (Player p : winnersList) {
            System.out.println(p + " has won " + award);
            p.awardChips(award);
        }
    }
    public void awardPlayerWhoHasNotFolded(){
        playersList.setCurrentPlayerTail();
        if (!playersList.getCurrentPlayer().hasFolded()) playersList.getCurrentPlayer().awardChips(pot);
        playersList.next();

        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            if (!playersList.getCurrentPlayer().hasFolded()) playersList.getCurrentPlayer().awardChips(pot);
            playersList.next();
        }
    }

    public void emptyCommunityCards(){
        communityCards = new ArrayList();
    }
    public void emptyPot(){pot=0;}

    public void dealCommunityCard(){
        communityCards.add(deck.takeACard());
    }
    public void dealCommunityCard(Card card){communityCards.add(card);} // auxiliary, only for testing and debugging
    public void setDefaultLastBetAmount(){
        playersList.setCurrentPlayerTail();
        playersList.getCurrentPlayer().setLastBetAmount(0);
        playersList.next();
        while (playersList.getCurrentPlayer() != playersList.getTailPlayer()){
            playersList.getCurrentPlayer().setLastBetAmount(0);
            playersList.next();
        }
    }
    public void displayCommunityCards(){
        System.out.println("  ");
        System.out.println("These are the community cards :");
        for (int i = 0; i < communityCards.size(); i++){
            System.out.println(communityCards.get(i));
        }
        System.out.println("  ");
    }


    public int getAmountToCall(){
        return amountToCall;
    }
    public void setAmountToCall(int amount){
        amountToCall = amount;
    }
    public void increasePot(int amount){
        pot = pot + amount;
    }
    public void setFirstToActPointer(Player player){
        firstToActPointer = player;
    }
    public void incrementPlayersFolded(){playersFolded = playersFolded + 1;}
    public Player getCurrentPlayer(){
        return playersList.getCurrentPlayer();
    }
    public Player getFirstToActPointer() {
        return firstToActPointer;
    }
    public int getPot(){return pot;}
    public List<Card> getCommunityCards(){return communityCards;}
    public List<Player> getTheBlinds(){
        return playersList.getTheBlinds();
    }
    public HandCombination getBestHandCombination(){return bestHandCombination;}
}

