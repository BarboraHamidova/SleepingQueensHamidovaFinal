package sq;

import java.util.*;

public class Hand {

    /*
    * Picks correct selected cards, throws them into discard pile and redraws*/

    private DrawingAndTrashPile drawingAndTrashPile;
    private Map<HandPosition, Card> positionCardMap;
    private Integer playerIdx;
    private Map<HandPosition, Card> pickedCards;

    public Hand(DrawingAndTrashPile pile, Integer playerIdx){
        this.drawingAndTrashPile = pile;
        List<Card> cardList =  drawingAndTrashPile.initialDraw();
        this.playerIdx = playerIdx;
        this.positionCardMap = new HashMap<>();
        for(int i = 0; i < cardList.size(); i++){
            positionCardMap.put(new HandPosition(i, playerIdx), cardList.get(i));
        }
    }

    public Optional<List<Card>> pickCards(List<HandPosition> positions){
        List<Card> picked = new ArrayList<>();
        this.pickedCards = new HashMap<>();
        for(HandPosition a : positions){
            picked.add(positionCardMap.get(a));
            pickedCards.put(a, positionCardMap.get(a));
        }
        return Optional.of(picked);
    }

    public Map<HandPosition, Card> removePickedCardsAndRedraw(){
        LinkedList<Card> newCards = new LinkedList<>(drawingAndTrashPile.discardAndDraw(new ArrayList<>(pickedCards.values())));
        for(HandPosition a : pickedCards.keySet()){
            positionCardMap.remove(a);
            positionCardMap.put(a, newCards.removeFirst());
        }
        return positionCardMap;
    }

    public List<Card> returnPickedCards(){
        return new ArrayList<>(pickedCards.values());
    }

    public HandPosition hasCardOfType(CardType type){
        for(HandPosition a : positionCardMap.keySet()){
            Card card = positionCardMap.get(a);
            if(card.tyoe.equals(type)){
                return a;
            }
        }
        return null;
    }

    public List<Card> getCards(){
        return new ArrayList<>(positionCardMap.values());
    }
}
