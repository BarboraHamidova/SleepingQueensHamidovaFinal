package sq;

public class HandPosition {
    private Integer cardIndex;
    private Integer playerIndex;

    public HandPosition(Integer cardIndex, Integer playerIndex){
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
