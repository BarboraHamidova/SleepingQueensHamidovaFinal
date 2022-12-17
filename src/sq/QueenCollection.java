package sq;

import java.util.*;

public class QueenCollection {
    private Map<Position, Queen> allQueens;

    private SleepingQueens sleepingQueens;

    public QueenCollection(SleepingQueens sleepingQueens){
        allQueens = new HashMap<>(sleepingQueens.getQueens());
        this.sleepingQueens = sleepingQueens;
    }

    public void addQueen(Queen queen, Position position){
        if(position.isSleepingQueen()){
            position = sleepingQueens.addQueen(queen);

        }
        else {
            position = new Position(position.getAwokenQueenPosition());
        }
        allQueens.put(position, queen);
    }

    public Optional<Queen> removeQueen(Position queenPosition){
        if(queenPosition.isSleepingQueen()){
            sleepingQueens.removeQueen(new Position(queenPosition.getSleepingQueenPosition()));
            return Optional.of(allQueens.remove(new Position(queenPosition.getSleepingQueenPosition())));
        }
        return Optional.of(allQueens.remove(new Position(queenPosition.getAwokenQueenPosition())));
    }

    public Map<Position, Queen> getQueens(){
        return allQueens;
    }
}
