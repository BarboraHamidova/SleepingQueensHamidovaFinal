package sq;

import java.util.*;

public class QueenCollection {
    private Map<Position, Queen> allQueens;

    private SleepingQueens sleepingQueens;

    public QueenCollection(SleepingQueens sleepingQueens){
        allQueens = new HashMap<>(sleepingQueens.getQueens());
    }

    public void addQueen(Queen queen, Position position){
        if(position.isSleepingQueen()){
            position = sleepingQueens.addQueen(queen);

        }
        allQueens.put(position, queen);
    }

    public Optional<Queen> removeQueen(Position queenPosition){
        if(queenPosition.isSleepingQueen()){
            sleepingQueens.removeQueen(queenPosition);
        }
        return Optional.of(allQueens.remove(queenPosition));
    }

    public Map<Position, Queen> getQueens(){
        return allQueens;
    }
}
