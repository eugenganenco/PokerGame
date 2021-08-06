import java.util.List;

public class Model {
    private final PokerTable pokerTable;
    private Turn turn;
    private final View view;
    public Model(View view){
        this.view = view;
        pokerTable = new PokerTable();
    }
    public void addPlayer(String name){
        pokerTable.addPlayer(new Player(name,1000,pokerTable));
    }
    public void startGame(){
        pokerTable.setRandomSmallBlind();

        turn = new Turn(pokerTable,view);




    }

    public Player getNextPlayer(){


        if (pokerTable.everyoneFolded()) {
            pokerTable.awardPlayerWhoHasNotFolded();
            turn = new Turn(pokerTable,view);
            return pokerTable.getCurrentPlayer();
        }

        pokerTable.nextPlayer();
        if (pokerTable.getCurrentPlayer() == pokerTable.getFirstToActPointer()){
            turn.setNextStage();
            if (turn.hasEnded()) {
                pokerTable.awardTheWinners(pokerTable.findTheWinnersOfTheTurn());
                view.displayTheWinners(pokerTable.getBestHandCombination(),pokerTable.findTheWinnersOfTheTurn());

                turn = new Turn(pokerTable,view);

                return pokerTable.getCurrentPlayer();
            }
            if (pokerTable.getCurrentPlayer().hasFolded()) this.getNextPlayer(); // danger!

            return pokerTable.getCurrentPlayer();
        }

        while (pokerTable.getCurrentPlayer().hasFolded()){
            pokerTable.nextPlayer();
            if (pokerTable.getCurrentPlayer() == pokerTable.getFirstToActPointer()){
                turn.setNextStage();

                if (turn.hasEnded()) {
                    pokerTable.awardTheWinners(pokerTable.findTheWinnersOfTheTurn());
                    view.displayTheWinners(pokerTable.getBestHandCombination(),pokerTable.findTheWinnersOfTheTurn());
                    turn = new Turn(pokerTable,view);

                    return pokerTable.getCurrentPlayer();
                }
                if (pokerTable.getCurrentPlayer().hasFolded()) this.getNextPlayer(); // danger!
                return pokerTable.getCurrentPlayer();
            }
        }

        return pokerTable.getCurrentPlayer();
    }

    public Player getCurrentPLayer(){
            return pokerTable.getCurrentPlayer();
        }
    public int getPot(){return pokerTable.getPot();}
    public int getAmountToCall(){ return pokerTable.getAmountToCall();}
    public List<Card> getCommunityCards(){ return pokerTable.getCommunityCards();}

}
