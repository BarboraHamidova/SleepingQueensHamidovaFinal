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


    Integer getCardIndex (){
        if(handPosition.isPresent()){
            return handPosition.get().getCardIndex();
        }
        if(awokenQueenPosition.isPresent()){
            return awokenQueenPosition.get().getCardIndex();
        }
        if(sleepingQueenPosition.isPresent()){
            return sleepingQueenPosition.get().getCardIndex();
        }
        return -1;
    }

    Integer getPlayerIndex(){
        if(handPosition.isPresent()){
            return handPosition.get().getPlayerIndex();
        }
        if(awokenQueenPosition.isPresent()){
            return awokenQueenPosition.get().getPlayerIndex();
        }
        if(sleepingQueenPosition.isPresent()){
            return null;
        }
        return -1;
    }
}
