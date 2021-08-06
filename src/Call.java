public class Call{
    public Call(Player player, PokerTable pokerTable){
        System.out.println(player + " has called");
        if (player.getChips() < pokerTable.getAmountToCall()) {
            pokerTable.increasePot(player.getChips());
            player.takeChips(player.getChips());
        }
        else {
            player.takeChips(pokerTable.getAmountToCall() - player.getLastBetAmount());
            player.setLastBetAmount(pokerTable.getAmountToCall() - player.getLastBetAmount());
            pokerTable.increasePot(pokerTable.getAmountToCall());
        }


    }



}
