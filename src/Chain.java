public abstract class  Chain {
    Chain nextInChain;
    PokerTable pokerTable;



    public void setNextInChain(Chain nextChain){

        this.nextInChain = nextChain;
    };
    public Chain getNextInChain() {
        pokerTable.setDefaultLastBetAmount();
        return this.nextInChain;

    }
    public abstract void execute();

}

