package sq;

import java.util.*;

public class GameObservable{
    private List<GameObserver> observerSet = new ArrayList<>();
    private Map<Integer, GameObserver> players = new HashMap<>();

    private String convertGameStateToString(GameState gameState){
        String string = "";
        string += "Pocet hracov: " + gameState.numberOfPlayers + "\n";
        string += "Na tahu bol: " + gameState.onTurn + "\n";
        string += "Spiace kralovne:";
        for(SleepingQueenPosition p : gameState.sleepingQueens){
            string += " " + p.getCardIndex();
        }
        string += "\n";
        string += "Karty:";
        for(HandPosition p : gameState.cards.keySet()){
            string += " " + p.getPlayerIndex() + "" + p.getCardIndex() + gameState.cards.get(p).get().tyoe + "" + gameState.cards.get(p).get().value;
        }
        string+="\n";
        string += "Bdele kralovne:";
        for(AwokenQueenPosition p : gameState.awokenQueens.keySet()){
            string +=  " " + p.getPlayerIndex() + "" + p.getCardIndex();
        }
        string += "\n";
        string += "Vyhodene karty toto kolo:";
        for(Card card : gameState.cardsDiscardedLastTurn){
            if (card.tyoe == CardType.Number){
                string += " " + card.value;
            }
            else {
                string += " " + card.tyoe;
            }
        }
        return string;
    }

    private String convertGameStateToStringForPlayer(GameState gameState, Integer playerIdx){
        String string = "";
        string += "Pocet hracov: " + gameState.numberOfPlayers + "\n";
        string += "Na tahu bol: " + gameState.onTurn + "\n";
        string += "Spiace kralovne:";
        for(SleepingQueenPosition p : gameState.sleepingQueens){
            string += " " + p.getCardIndex();
        }
        string += "\n";
        string += "Karty:";
        for(HandPosition p : gameState.cards.keySet()){
            if(p.getPlayerIndex() == playerIdx) {
                string += " " + p.getPlayerIndex() + "" + p.getCardIndex() + " ";
                if(gameState.cards.get(p).isPresent()){
                    string += gameState.cards.get(p).get();
                }
            }
        }
        string+="\n";
        string += "Bdele kralovne:";
        int num = 0;
        for(AwokenQueenPosition p : gameState.awokenQueens.keySet()){
            if(p.getPlayerIndex() == playerIdx){
                num += 1;
            }
        }
        string += num + "\n";
        string += "Vyhodene karty toto kolo:";
        for(Card card : gameState.cardsDiscardedLastTurn){
            if (card.tyoe == CardType.Number){
                string += " " + card.value;
            }
            else {
                string += " " + card.tyoe;
            }
        }
        return string;
    }

    public void add(GameObserver observer){
        observerSet.add(observer);
    }

    public void addPlayer(Integer playerIdx, GameObserver observer){
        players.put(playerIdx, observer);
    }

    public void remove(GameObserver observer){
        observerSet.remove(observer);
    }

    public void notifyAll(GameState message){
        for(GameObserver observer : observerSet){
            observer.notify(convertGameStateToString(message));
        }
        for(Integer playerIdx : players.keySet()){
            players.get(playerIdx).notify();
        }
    }
}
