import java.util.List;

public class Player {
    private PlayerState playerState;

    /*Picks selected cards and determines what class is responsible for evaluating the play
    * Consumes cards played from Hand on success
    * Somehow needs to keep playerState*/

    public void play(List<Position> cards){

    }

    public PlayerState getPlayerState(){
        return this.playerState;
    }
}
