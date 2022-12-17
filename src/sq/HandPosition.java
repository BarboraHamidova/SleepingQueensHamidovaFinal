package sq;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            HandPosition handPosition = (HandPosition)o;
            return this.cardIndex == handPosition.getCardIndex() && this.playerIndex == handPosition.getPlayerIndex();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.cardIndex, this.playerIndex});
    }
}
