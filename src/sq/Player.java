package sq;

import java.util.*;

public class Player {
    private PlayerState playerState;
    private Integer playerIdx;
    private Hand hand;

    /*Picks selected cards and determines what class is responsible for evaluating the play
    * Consumes cards played from sq.Hand on success
    * Somehow needs to keep playerState*/

    public Player(Integer playerIdx, DrawingAndTrashPile pile){
        this.playerIdx = playerIdx;
        this.hand = new Hand(pile, this.playerIdx);
        this.playerState = new PlayerState();
        this.playerState.awokenQueens = new HashMap<>();
        this.playerState.cards = new HashMap<>();
        List<Card> cardList = hand.getCards();
        for(int i = 0; i < cardList.size(); i++){
            playerState.cards.put(i, Optional.of(cardList.get(i)));
        }

    }

    public void play(List<Position> cards){
        List<HandPosition> discard = new LinkedList<>();
        for(Position p : cards){
            if(p.isHand() && p.isSleepingQueen()){
                /*Vyhodim krala z ruky, o kralovnu sa ma nieco ine postarat*/
                if(p.getHandPlayerIndex() == playerIdx && playerState.cards.get(p.getHandCardIndex()).isPresent()){
                    Card c = playerState.cards.get(p.getHandCardIndex()).orElse(null);
                    if(c.tyoe == CardType.King){
                        discard.add(p.getHandPosition());
                    }
                }
            }
        }
        hand.pickCards(discard);
        Map<HandPosition, Card> newCards = hand.removePickedCardsAndRedraw();
        for(HandPosition p : newCards.keySet()){
            playerState.cards.put(p.getCardIndex(), Optional.of(newCards.get(p)));
        }

    }

    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
