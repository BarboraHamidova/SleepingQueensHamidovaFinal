package sq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game implements GameFinishedStrategy {
    private Integer numberOfPlayers;
    private List<Player> players;
    private DrawingAndTrashPile pile;
    private QueenCollection queenCollection;
    private SleepingQueens sleepingQueens;
    private GameState gameState;

    public Game(Integer numberOfPlayers){
        //mozno by mohol dostat list kariet a tak vytvorit pile
        this.sleepingQueens = new SleepingQueens();
        this.queenCollection = new QueenCollection(sleepingQueens);
        this.numberOfPlayers = numberOfPlayers;
        this.pile = new DrawingAndTrashPile(5, 0);
        players = new ArrayList<>();
    }

    public boolean addPlayer(Player player){
        if(this.players.size() < numberOfPlayers) {
            this.players.add(player);
            return true;
        }
        return false;
    }

    public QueenCollection getQueenCollection(){
        return this.queenCollection;
    }

    public Game(Integer numberOfPlayers, DrawingAndTrashPile pile){
        //mozno by mohol dostat list kariet a tak vytvorit pile
        this.sleepingQueens = new SleepingQueens();
        this.queenCollection = new QueenCollection(sleepingQueens);
        this.numberOfPlayers = numberOfPlayers;
        this.pile = pile;
        players = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new Player(i, pile, this, queenCollection));
        }
    }

    public Player getPlayer(Integer playerIdx){
        return players.get(playerIdx);
    }

    @Override
    public Optional<Integer> isFinished(){
        if(sleepingQueens.getQueens().isEmpty()){
            int maxPoints = 0;
            Optional<Integer> result = Optional.empty();
            for(Player player : players){
                int points = 0;
                for(Optional<Card> card : player.getPlayerState().cards.values()){
                    if(card.isPresent()){
                        points += card.get().value;
                    }
                }
                if(points > maxPoints){
                    maxPoints = points;
                    result = Optional.of(player.getPlayerIdx());
                }
            }
            return result;
        }

        for(Player player : players){
            int numberOfQueens = player.getPlayerState().awokenQueens.size();
            int numberOfPoints = 0;
            for(Optional<Card> card : player.getPlayerState().cards.values()){
                if(card.isPresent()){
                    numberOfPoints += card.get().value;
                }
            }
            if(numberOfPlayers >= 2 && numberOfPlayers <= 3 && (numberOfQueens >= 5 || numberOfPoints >= 50)){
                return Optional.of(player.getPlayerIdx());
            }
            else if(numberOfPlayers >= 4 && numberOfPlayers <= 5 && (numberOfQueens >= 4 || numberOfPoints >= 40)){
                return Optional.of(player.getPlayerIdx());
            }
        }
        return Optional.empty();

    }

    public Optional<GameState> play(Integer playerIdx, List<Position> cards){
        this.pile.newTurn();
        players.get(playerIdx).play(cards);
        this.gameState = new GameState(numberOfPlayers, playerIdx, queenCollection, players, pile);
        return Optional.of(gameState);
    }

}
