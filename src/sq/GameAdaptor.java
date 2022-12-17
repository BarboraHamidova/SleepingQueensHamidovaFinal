package sq;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GameAdaptor implements GamePlayerInterface {
    private Game game;
    private GameObservable gameObservable;

    private List<Position> convertStringToPositions(String cards, Integer playerIdx){
        List<Position> positions = new LinkedList<>();
        String[] strings = cards.split(" ");
        HandPosition lastHandPosition = null;
        for(int i = 0; i < strings.length; i++){
            System.out.println(strings[i].charAt(0));
            if(strings[i].charAt(0) == 'h' && lastHandPosition == null){
                lastHandPosition = new HandPosition(Character.getNumericValue(strings[i].charAt(1)) - 1, playerIdx);
            }
            else if(strings[i].charAt(0) == 'h'){
                positions.add(new Position(lastHandPosition));
                lastHandPosition = new HandPosition(Character.getNumericValue(strings[i].charAt(1))-1, playerIdx);
            }
            else if(strings[i].charAt(0) == 'a'){
                System.out.println();
                positions.add(new Position(lastHandPosition, new AwokenQueenPosition(Character.getNumericValue(strings[i].charAt(2)) - 1, Character.getNumericValue(strings[i].charAt(1)) - 1)));
                lastHandPosition = null;
            }
            else if(strings[i].charAt(0) == 's'){
                positions.add(new Position(lastHandPosition, new SleepingQueenPosition(Character.getNumericValue(strings[i].charAt(1)) - 1)));
                lastHandPosition = null;
            }
        }
        if(lastHandPosition != null){
            positions.add(new Position(lastHandPosition));
        }
        return positions;
    }

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
            string += " " + p.getPlayerIndex() + "" + p.getCardIndex();
        }
        string+="\n";
        string += "Bdele kralovne:";
        for(AwokenQueenPosition p : gameState.awokenQueens.keySet()){
            string +=  " " + p.getPlayerIndex() + "" + p.getCardIndex();
        }
        string += "\n";
        string += "Vyhodene karty toto kolo:";
        for(Card card : gameState.cardsDiscardedLastTurn){
            if(card != null) {
                if (card.tyoe == CardType.Number) {
                    string += " " + card.value;
                } else {
                    string += " " + card.tyoe;
                }
            }
        }
        return string;
    }

    public GameAdaptor(Game game, GameObservable go){
        this.game = game;
        this.gameObservable = go;
    }

    @Override
    public String play(String player, String cards){
        /*Converts to given format, calls correct implemented function and converts to interface format*/
        Integer playerIdxInt = Integer.valueOf(player) - 1;
        List<Position> cardsPositions = convertStringToPositions(cards, playerIdxInt);
        for(Position p : cardsPositions){
            System.out.println(p);
        }
        Optional<GameState> gameState = game.play(playerIdxInt ,cardsPositions);
        String result = "";
        if(game.isFinished().isPresent()){
            result += "Vyhral: " + game.isFinished().get() + "\n";
        }
        if(gameState.isPresent()){
            GameState gS = gameState.get();
            result += convertGameStateToString(gS);
            gameObservable.notifyAll(gS);

        }
        return result;
    }
}
