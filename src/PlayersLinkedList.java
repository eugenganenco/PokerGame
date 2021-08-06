import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;

public class PlayersLinkedList implements circularLinkedList {
    private int size;
    private Node currentNode;
    private Node head;
    private Node tail;
    private Node smallBlind;


    public PlayersLinkedList(){
        size = 0;
        tail = null;
        head = null;
        currentNode = null;
        smallBlind = null;


    }

    public int size() {
        return size;
    }


    public void addPlayer(Player player) {
        if (tail==null){
            tail = new Node(player);
            head = tail;
        }
        else {
            head.setNext(new Node(player));
            head = head.getNext();
        }
        size++;
        head.setNext(tail);

    }
    public void next() {
        currentNode = currentNode.getNext();

    }
    public void setNewSmallBlind(){
        smallBlind = smallBlind.getNext();
    }
    public void setRandomSmallBlind() {
        Random rand = new Random();
        int id = rand.nextInt(size);
        smallBlind = tail;
        for (int i = 0; i <= id; i++){
            smallBlind = smallBlind.getNext();
        }
        System.out.println("Small blind is : " + smallBlind.getPlayer());
    }

    public void ejectCurrentPlayer(){
        Node nodePointer = tail;
        while (nodePointer.getNext().getPlayer() != currentNode.getPlayer())
            nodePointer = nodePointer.getNext();

        if (currentNode == tail){
            nodePointer.setNext(currentNode.getNext());
            tail = currentNode.getNext();
            currentNode = currentNode.getNext();
        }
        else if (currentNode == head){
            nodePointer.setNext(currentNode.getNext());
            head = nodePointer;
            currentNode = currentNode.getNext();
        }

        else {
            nodePointer.setNext(currentNode.getNext());
            currentNode = currentNode.getNext();
        }
        size--;
    }

    public void setCurrentPlayerTail(){
        currentNode = tail;
    }
    public void setCurrentPlayerUTG(){
        currentNode = smallBlind.getNext().getNext();
    }
    public void setCurrentPlayerSB(){
        currentNode = smallBlind;
    }
    public Player getCurrentPlayer(){
        return currentNode.getPlayer();
    }


    public Player getTailPlayer(){
        return tail.getPlayer();
    }
    public List<Player> getTheBlinds() {
        List <Player> theBlinds = new ArrayList<>(2);
        theBlinds.add(smallBlind.getPlayer());
        theBlinds.add(smallBlind.getNext().getPlayer());
        return theBlinds;
    }

}
