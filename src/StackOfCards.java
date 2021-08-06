import java.util.*;

public class StackOfCards {
    private final Stack<Card> stackOfCards;

    public StackOfCards(){
        stackOfCards = new Stack<Card>();
        this.stackOfCards.empty();
        int[] cards = new int[52];
        int j, k;
        Random rand = new Random();
        for (int i = 0; i < 52; i++) {
            cards[i] = i;
        }
        for (int i = 0; i < 52; i++) {
            j = rand.nextInt(52);
            k = cards[i];
            cards[i] = cards[j];
            cards[j] = k;
        }
        for (int i = 0; i < 52; i++) {
            this.stackOfCards.push(new Card(cards[i]));
        }

    }

    public Card takeACard(){
        return stackOfCards.pop();
        }

    public void display(){
        for (int i = 0; i < 52; i++) {
            System.out.println(stackOfCards.elementAt(i));
        }
    }
    }
