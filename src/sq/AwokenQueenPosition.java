package sq;

import java.util.Objects;

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

    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            AwokenQueenPosition p = (AwokenQueenPosition)o;
            return this.cardIndex == p.getCardIndex() && this.playerIndex == p.getPlayerIndex();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.cardIndex, this.playerIndex});
    }
}
