public class River extends Chain{



    public River(PokerTable pokerTable){
        this.pokerTable = pokerTable;

        nextInChain = null;

    }

    public void execute() {
        pokerTable.setAmountToCall(0);
        pokerTable.setPlayerToActFlop();
        System.out.println("  ");
        System.out.println(" River ");
        System.out.println("  ");
        pokerTable.displayCommunityCards();
        pokerTable.dealCommunityCard();

    }

}