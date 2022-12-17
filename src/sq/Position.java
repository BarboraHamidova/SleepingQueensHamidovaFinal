package sq;

import java.util.Objects;
import java.util.Optional;

public class Position {
    private Optional<HandPosition> handPosition;
    private Optional<AwokenQueenPosition> awokenQueenPosition;
    private Optional<SleepingQueenPosition> sleepingQueenPosition;

    public Position(HandPosition position){
        handPosition = Optional.of(position);
        this.sleepingQueenPosition = Optional.empty();
        this.awokenQueenPosition = Optional.empty();
    }

    public Position(AwokenQueenPosition position){
        awokenQueenPosition = Optional.of(position);
        this.sleepingQueenPosition = Optional.empty();
        this.handPosition = Optional.empty();
    }

    public Position(SleepingQueenPosition position){
        sleepingQueenPosition = Optional.of(position);
        this.handPosition = Optional.empty();
        this.awokenQueenPosition = Optional.empty();
    }

    public Position(HandPosition hand, SleepingQueenPosition queen){
        this.handPosition = Optional.of(hand);
        this.sleepingQueenPosition = Optional.of(queen);
        this.awokenQueenPosition = Optional.empty();
    }

    public Position(HandPosition hand, AwokenQueenPosition queen){
        this.handPosition = Optional.of(hand);
        this.awokenQueenPosition = Optional.of(queen);
        this.sleepingQueenPosition = Optional.empty();
    }

    public HandPosition getHandPosition(){
        return this.handPosition.orElse(null);
    }

    public SleepingQueenPosition getSleepingQueenPosition(){
        return this.sleepingQueenPosition.orElse(null);
    }

    public AwokenQueenPosition getAwokenQueenPosition(){
        return this.awokenQueenPosition.orElse(null);
    }


    public Integer getHandCardIndex (){
        if(handPosition.isPresent()){
            return handPosition.get().getCardIndex();
        }
        return -1;
    }

    public Integer getSleepingQueenCardIndex (){
        if(sleepingQueenPosition.isPresent()){
            return sleepingQueenPosition.get().getCardIndex();
        }
        return -1;
    }

    public Integer getAwokenCardIndex (){
        if(awokenQueenPosition.isPresent()){
            return awokenQueenPosition.get().getCardIndex();
        }
        return -1;
    }

    public Integer getHandPlayerIndex(){
        if(handPosition.isPresent()){
            return handPosition.get().getPlayerIndex();
        }
        return -1;
    }

    public Integer getAwokenQueenPlayerIndex(){
        if(awokenQueenPosition.isPresent()){
            return awokenQueenPosition.get().getPlayerIndex();
        }
        return -1;
    }

    public boolean isHand(){
        return handPosition.isPresent();
    }

    public boolean isAwokenQueen(){
        return awokenQueenPosition.isPresent();
    }

    public boolean isSleepingQueen(){
        return sleepingQueenPosition.isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }

        if(this.isHand() && !this.getHandPosition().equals(((Position) o).getHandPosition())){
            return false;
        }

        if(this.isSleepingQueen() && !this.getSleepingQueenPosition().equals(((Position) o).getSleepingQueenPosition())){
            return false;
        }

        if(this.isAwokenQueen() && !this.getAwokenQueenPosition().equals(((Position) o).getAwokenQueenPosition())){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(handPosition.hashCode(), awokenQueenPosition.hashCode(), sleepingQueenPosition.hashCode());
    }

    @Override
    public String toString(){
        String result = "";
        if(handPosition.isPresent()){
            result += "h" + handPosition.get().getPlayerIndex() + "" + handPosition.get().getCardIndex();
        }
        if(sleepingQueenPosition.isPresent()){
            result += "s" + sleepingQueenPosition.get().getCardIndex();
        }
        if(awokenQueenPosition.isPresent()){
            result += "a" + awokenQueenPosition.get().getCardIndex();
        }
        return result;
    }

}
