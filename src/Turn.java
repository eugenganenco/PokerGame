public class Turn {
    private Chain currentStage;
    private final PokerTable pokerTable;
    private boolean hasEnded;

    public Turn(PokerTable pokerTable, View view){
        this.pokerTable = pokerTable;
        this.pokerTable.setNewSmallBlind();
        this.hasEnded = false;
        Chain preFlop = new PreFlop(pokerTable,view);
        this.currentStage = preFlop;


        Chain flop = new Flop(pokerTable);
        Chain fourthStreet = new FourthStreet(pokerTable);
        Chain river = new River(pokerTable);

        preFlop.setNextInChain(flop);
        flop.setNextInChain(fourthStreet);
        fourthStreet.setNextInChain(river);
        preFlop.execute();

    }
    public void setNextStage(){
        currentStage = currentStage.getNextInChain();
        if (currentStage != null)
        currentStage.execute();
        else this.hasEnded = true;
    }

    public boolean hasEnded(){
        return this.hasEnded;
    }
}


