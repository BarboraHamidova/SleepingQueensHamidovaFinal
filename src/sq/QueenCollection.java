package sq;

import java.util.*;

public class QueenCollection {
    private Map<Position, Queen> allQueens;

    private SleepingQueens sleepingQueens;
    private AwokenQueens awokenQueens;

    public QueenCollection(SleepingQueens sleepingQueens, AwokenQueens awokenQueens){
        allQueens = new HashMap<>(sleepingQueens.getQueens());
    }

    public void addQueen(Queen queen, Position position){
        if(position.isSleepingQueen()){
            sleepingQueens.addQueen(position, queen);
        }
        if(position.isAwokenQueen()){
            awokenQueens.addQueen(position, queen);
        }
        allQueens.put(position, queen);
    }

    public Optional<Queen> removeQueen(Position queenPosition){
        return Optional.of(allQueens.remove(queenPosition));
    }

    public Map<Position, Queen> getQueens(){
        return allQueens;
    }
}
