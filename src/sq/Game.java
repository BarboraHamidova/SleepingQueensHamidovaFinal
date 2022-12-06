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
        /*No idea what to do*/
        return Optional.empty();
    }

    public Optional<GameState> play(Integer playerIdx, List<Position> cards){
        players.get(playerIdx).play(cards);
        /*Has to compose GameState*/
        /*prejde cez vsetkych playerov a updatne gamestate*/
        return Optional.empty();
    }

}
