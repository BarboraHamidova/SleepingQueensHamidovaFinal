package sq;

import java.util.Optional;

public class Position {
    private Optional<HandPosition> handPosition;
    private Optional<AwokenQueenPosition> awokenQueenPosition;
    private Optional<SleepingQueenPosition> sleepingQueenPosition;

    public Position(HandPosition position){
        handPosition = Optional.of(position);
    }

    public Position(AwokenQueenPosition position){
        awokenQueenPosition = Optional.of(position);
    }

    public Position(SleepingQueenPosition position){
        sleepingQueenPosition = Optional.of(position);
    }

    public Position(HandPosition hand, SleepingQueenPosition queen){
        this.handPosition = Optional.of(hand);
        this.sleepingQueenPosition = Optional.of(queen);
    }

    public Position(HandPosition hand, AwokenQueenPosition queen){
        this.handPosition = Optional.of(hand);
        this.awokenQueenPosition = Optional.of(queen);
    }

    public HandPosition getHandPosition(){
        return this.handPosition.orElse(null);
    }

    public SleepingQueenPosition getSleepingQueenPosition(){
        return this.sleepingQueenPosition.orElse(null);
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

}
