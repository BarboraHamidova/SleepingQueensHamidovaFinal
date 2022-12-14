package sq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestingHand {
    public static void main(String[] args) {
        Hand hand = new Hand(new DrawingAndTrashPile(5, 0), 0);
        List<Card> cards = hand.getCards();
        for(Card c : cards){
            System.out.println(c.tyoe + " " + c.value);
        }
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 0));
        hand.pickCards(positions);
        List<Card> pickedCards = hand.returnPickedCards();
        System.out.println("Picked cards: ");
        for(Card c : pickedCards){
            System.out.println(c.tyoe + " " + c.value);
        }
        hand.removePickedCardsAndRedraw();
        System.out.println("New cards: ");
        List<Card> newCards = hand.getCards();
        for(Card c : newCards){
            System.out.println(c.tyoe + " " + c.value);
        }
        for(CardType cardType : CardType.values()){
            System.out.println(cardType + " " + hand.hasCardOfType(cardType));
        }
    }
}
