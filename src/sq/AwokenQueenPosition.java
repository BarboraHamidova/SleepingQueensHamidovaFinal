package sq;

public class AwokenQueenPosition {
    private Integer cardIndex;
    private Integer playerIndex;

    public AwokenQueenPosition(Integer cardIndex, Integer playerIndex){
        this.cardIndex = cardIndex;
        this.playerIndex = playerIndex;
    }

    Integer getCardIndex (){
        return cardIndex;
    }

    Integer getPlayerIndex(){
        return playerIndex;
    }
}
