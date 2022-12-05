package sq;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

public class DrawingAndTrashPile {
    private List<Card> discardPile;
    private LinkedList<Card> drawPile;
    private List<Card> thisTurnDiscard;
    private Integer cardsPerPlayer;

    private void restartDrawPile(){
        /*zalezi od typu hry, podla typu hry zavolaj spravnu metodu na obnovanie draw pile
        * zatial len provizorne*/
        drawPile.addAll(discardPile);
        discardPile = new ArrayList<>();
    }

    public DrawingAndTrashPile(Integer numberOfCards, Integer cardsPerPlayer){
        /*Treba doimplementovat, podla toho, co bude treba, podla hratelnosti*/
        this.cardsPerPlayer = cardsPerPlayer;
    }

    public List<Card> initialDraw(){
        List<Card> result = new ArrayList<>();
        for(int i = 0; i < cardsPerPlayer; i++){
            result.add(drawPile.removeFirst());
        }
        return result;
    }

    public List<Card> discardAndDraw(List<Card> discard){
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
