package sq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Hand {

    /*
    * Picks correct selected cards, throws them into discard pile and redraws*/

    private List<Card> cardsOfHand;

    public Optional<List<Card>> pickCards(List<HandPosition> positions){
        return Optional.empty();
    }

    public Map<HandPosition, Card> removePickedCardsAndRedraw(){
        return new HashMap<>();
    }

    public void returnPickedCards(){

    }

    public HandPosition hasCardOfType(CardType type){
        return new HandPosition();
    }

    public List<Card> getCards(){
        return cardsOfHand;
    }
}
