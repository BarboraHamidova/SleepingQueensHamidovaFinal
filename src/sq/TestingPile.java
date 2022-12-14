package sq;

import java.util.List;

public class TestingPile {
    public static void main(String[] args) {
        DrawingAndTrashPile pile = new DrawingAndTrashPile(5, 0);
        List<Card> cards = pile.initialDraw();
        for(Card c : cards){
            System.out.println(c.tyoe + " " + c.value);
        }
        List<Card> newCards = pile.discardAndDraw(cards);
        List<Card> discarded = pile.getCardsDiscardedThisTurn();
        System.out.println("Discarded cards: ");
        for(Card c : discarded){
            System.out.println(c.tyoe + " " + c.value);
        }
        System.out.println("New cards: ");
        for(Card c : newCards){
            System.out.println(c.tyoe + " " + c.value);
        }
        pile.newTurn();
        if(pile.getCardsDiscardedThisTurn().isEmpty()){
            System.out.println("Empty");
        }
    }
}
