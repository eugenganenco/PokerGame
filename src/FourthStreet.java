public class FourthStreet extends Chain{


    public FourthStreet(PokerTable pokerTable){
        this.pokerTable = pokerTable;

    }

    public void execute() {
        pokerTable.setAmountToCall(0);
        pokerTable.setPlayerToActFlop();
        System.out.println("  ");
        System.out.println(" Turn ");
        System.out.println("  ");
        pokerTable.dealCommunityCard();
        pokerTable.displayCommunityCards();



    }
}