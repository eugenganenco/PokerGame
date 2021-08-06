public class Flop extends Chain{


    public Flop(PokerTable pokerTable){
        this.pokerTable = pokerTable;

    }

    public void execute() {
        pokerTable.setAmountToCall(0);
        pokerTable.setPlayerToActFlop();
        System.out.println("  ");
        System.out.println(" Flop ");
        System.out.println("  ");
        pokerTable.dealCommunityCard();
        pokerTable.dealCommunityCard();
        pokerTable.dealCommunityCard();
        pokerTable.displayCommunityCards();




    }

}