import java.util.List;

public class Controller {
    private View view;
    private Model model;
    public void addView(View view){ this.view = view;}
    public void addModel(Model model){this.model = model;}
    public void addPlayer(String name){
        System.out.println(name + "player added");
        model.addPlayer(name);
    }

    public void updateCommunityCards(){}
    public void startGame(){
        model.startGame();
    }

    public Player getCurrentPlayer(){
        return model.getCurrentPLayer();
    }
    public Player getNextPlayer(){
        return model.getNextPlayer();
    }
    public int getPot() { return model.getPot();}
    public int getAmountToCall(){ return model.getAmountToCall();}
    public List<Card> getCommunityCards(){return model.getCommunityCards();}
}
