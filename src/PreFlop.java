
public class PreFlop extends Chain{
    View view;
    boolean isFirstTurn;

    public PreFlop(PokerTable pokerTable, View view){
        this.view = view;
        this.pokerTable = pokerTable;
        this.isFirstTurn = true;
    }

    public void execute() {
        System.out.println("  ");
        System.out.println(" Preflop ");
        System.out.println("  ");

        view.unfoldThePlayers();
        view.ejectLosers(pokerTable.getEjectedLosers());
        if (pokerTable.onePLayerLeft()) view.displayTheWinnerOfTheGame(pokerTable.getTheRemainingPlayer());
        view.setTheBlinds(pokerTable.getTheBlinds());


        pokerTable.emptyCommunityCards();
        pokerTable.emptyPot();
        pokerTable.setAmountToCall(0);
        pokerTable.unfoldEveryone();
        pokerTable.emptyHands();
        pokerTable.shuffleCards();
        pokerTable.distributeCards();
        pokerTable.setPlayerToActPreFlop();
        this.isFirstTurn = false;

        }
    }

