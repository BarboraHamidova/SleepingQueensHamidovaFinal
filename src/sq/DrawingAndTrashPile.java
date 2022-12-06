package sq;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DrawingAndTrashPile {
    private final Integer variant;
    private LinkedList<Card> discardPile;
    private LinkedList<Card> drawPile;
    private List<Card> thisTurnDiscard;
    private Integer cardsPerPlayer;

    private LinkedList<Card> shuffle(LinkedList<Card> toShuffle){
        Random random = new Random();
        Integer length = toShuffle.size();
        LinkedList<Card> newPile = new LinkedList<>();
        for(int i = 0; i < length; i++){
            newPile.add(toShuffle.remove(random.nextInt(length-1-i)));
        }
        return newPile;
    }

    private void restartDrawPile(){
        /*zalezi od typu hry, podla typu hry zavolaj spravnu metodu na obnovanie draw pile
        * zatial len provizorne*/
        if(variant == 0) {
            discardPile = shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile = new LinkedList<>();
        }
        if(variant == 1){
            discardPile = shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile = new LinkedList<>();
        }
    }

    public DrawingAndTrashPile(Integer cardsPerPlayer, Integer variant){
        this.variant = variant;
        this.cardsPerPlayer = cardsPerPlayer;
        this.drawPile = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            drawPile.add(new Card(CardType.King, 0));
        }
        for(int i = 0; i < 4; i++){
            drawPile.add(new Card(CardType.Knight, 0));
            drawPile.add(new Card(CardType.SleepingPotion, 0));
        }
        for(int i = 0; i < 3; i++){
            drawPile.add(new Card(CardType.Dragon, 0));
            drawPile.add(new Card(CardType.MagicWand, 0));
        }
        for(int i = 1; i <= 10; i++){
            for(int j = 0; j < 4; j++){
                drawPile.add(new Card(CardType.Number, i));
            }
        }
        drawPile = shuffle(drawPile);
        discardPile = new LinkedList<>();

    }

    public List<Card> initialDraw(){
        List<Card> result = new ArrayList<>();
        for(int i = 0; i < cardsPerPlayer; i++){
            result.add(drawPile.removeFirst());
        }
        return result;
    }

    public List<Card> discardAndDraw(List<Card> discard){
        if(variant == 1 && discard.size() > drawPile.size()){
            restartDrawPile();
        }
        discardPile.addAll(discard);
        thisTurnDiscard.addAll(discard);
        List<Card> result = new ArrayList<>();
        for(int i = 0; i < discard.size(); i++){
            if(drawPile.isEmpty()){
                restartDrawPile();
            }
            result.add(drawPile.removeFirst());
        }
        return result;
    }

    public void newTurn(){
        thisTurnDiscard = new ArrayList<>();

    }

    public List<Card> getCardsDiscardedThisTurn(){
        return thisTurnDiscard;
    }


}
