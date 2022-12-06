package sq;

import java.util.*;

public class Player {
    private PlayerState playerState;
    private Integer playerIdx;
    private Hand hand;
    private Game game;
    private AwokenQueens awokenQueens;

    /*Picks selected cards and determines what class is responsible for evaluating the play
    * Consumes cards played from sq.Hand on success
    * Somehow needs to keep playerState*/

    public Player(Integer playerIdx, DrawingAndTrashPile pile, Game game){
        this.playerIdx = playerIdx;
        this.hand = new Hand(pile, this.playerIdx);
        this.game = game;
        this.playerState = new PlayerState();
        this.playerState.awokenQueens = new HashMap<>();
        this.playerState.cards = new HashMap<>();
        List<Card> cardList = hand.getCards();
        for(int i = 0; i < cardList.size(); i++){
            playerState.cards.put(i, Optional.of(cardList.get(i)));
        }

    }

    public Hand getHand(){
        return this.hand;
    }

    /*public void updateStateCards(Map<HandPosition, Card> map){
        for(HandPosition h : map.keySet()){
            playerState.cards.put(h.getCardIndex(), Optional.of(map.get(h)));
        }
    }*/

    public void play(List<Position> cards){
        List<HandPosition> discard = new LinkedList<>();
        Map<HandPosition, Card> numberedCardsAndPosition = new HashMap<>();
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
            if(p.isHand() && p.isAwokenQueen()){
                if(p.getHandPlayerIndex() == playerIdx && playerState.cards.get(p.getHandCardIndex()).isPresent()){
                    CardType type = playerState.cards.get(p.getHandCardIndex()).orElse(null).tyoe;
                    EvaluateAttack attack =  new EvaluateAttack(type, game.getPlayer(p.getAwokenQueenPlayerIndex()), this);
                    Boolean success = attack.play(p, p.getAwokenQueenPlayerIndex());
                    if(!success){
                        discard.add(p.getHandPosition());
                    }
                    else {
                        //Somehow needs to get queen
                    }
                }
            }
            else if(p.isHand() && p.getHandPlayerIndex() == playerIdx && playerState.cards.get(p.getHandPlayerIndex()).isPresent()){
                Card c = playerState.cards.get(p.getHandCardIndex()).orElse(null);
                if(c.tyoe == CardType.Number){
                    numberedCardsAndPosition.put(p.getHandPosition(), c);
                }
            }
        }
        boolean canDiscard = new EvaluateNumberedCards().play(new LinkedList<>(numberedCardsAndPosition.values()));
        if(canDiscard){
            discard.addAll(numberedCardsAndPosition.keySet());
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
