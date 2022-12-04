package sq;

import java.util.List;
import java.util.Optional;

public class Game implements GameFinishedStrategy {
    private Integer numberOfPlayers;

    @Override
    public Optional<Integer> isFinished(){
        /*No idea what to do*/
        return Optional.empty();
    }

    public Optional<GameState> play(Integer playerIdx, List<Position> cards){
        Optional<GameState> optionalGameState = Optional.empty();
        /*Composes somehow gameState and returns it, no idea how this works*/
        return optionalGameState;

    }

}
