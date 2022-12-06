package sq;

import sq.AwokenQueenPosition;
import sq.Card;

import java.util.*;

public class GameState {
    public final Integer numberOfPlayers;
    public Integer onTurn;
    public Set<SleepingQueenPosition> sleepingQueens;
    public Map<HandPosition, Optional<Card>> cards;
    public Map<AwokenQueenPosition, Queen> awokenQueens;
    public List<Card> cardsDiscardedLastTurn;

    /* Probably everything should be final and constructor should initialize everything */

    public GameState(int numberOfPlayers, int onTurn, QueenCollection queenCollection, List<Player> players, DrawingAndTrashPile pile){
        this.numberOfPlayers = numberOfPlayers;
        this.onTurn = onTurn;
        this.sleepingQueens = new HashSet<>();
        this.awokenQueens = new HashMap<>();
        for(Position p : queenCollection.getQueens().keySet()){
            if(p.isAwokenQueen()){
                awokenQueens.put(p.getAwokenQueenPosition(), queenCollection.getQueens().get(p));
            }
            else if(p.isSleepingQueen()){
                sleepingQueens.add(p.getSleepingQueenPosition());
            }
        }
        this.cards = new HashMap<>();
        for(Player player : players){
            for(Integer cardsIndx : player.getPlayerState().cards.keySet()){
                cards.put(new HandPosition(cardsIndx, player.getPlayerIdx()), player.getPlayerState().cards.get(cardsIndx));
            }
        }
        this.cardsDiscardedLastTurn = pile.getCardsDiscardedThisTurn();


    }

}
