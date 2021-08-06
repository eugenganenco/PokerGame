public class Node {
    private Node next;
    private Player player;

    public Node(Player player){
        this.player = player;
        next = null;
    }
    public void setNext(Node nextNode){
        next = nextNode;
    }
    public Node getNext(){
        return next;
    }
    public Player getPlayer(){
        return player;
    }
}
