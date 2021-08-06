public class Raise {
    public Raise(Player player, PokerTable pokerTable, int amount){
        if (amount > player.getChips()){
            pokerTable.increasePot(player.getChips());
            if (player.getChips() > pokerTable.getAmountToCall())
                pokerTable.setAmountToCall(player.getChips());
            pokerTable.setFirstToActPointer(player);
            player.takeChips(player.getChips());
        }
        else {
            pokerTable.setAmountToCall(amount);
            pokerTable.increasePot(amount);
            pokerTable.setFirstToActPointer(player);
            player.setLastBetAmount(amount);
            player.takeChips(amount);
        }

    }
}
