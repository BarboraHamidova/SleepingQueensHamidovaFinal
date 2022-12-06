package sq;

import java.util.*;

public class SleepingQueens {
    private Map<Position, Queen> queens;
    private final TreeSet<Integer> indices = initializeSet();

    private TreeSet<Integer> initializeSet(){
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < 12; i++){
            set.add(i);
        }
        return set;
    }

    public SleepingQueens(){
        queens = new HashMap<>();
        for (int i = 0; i < 12; i++){
            Queen queen = new Queen();
            Position position = new Position(new SleepingQueenPosition(i));
            queens.put(position, queen);
        }
    }

    public Position addQueen(Queen queen){
        TreeSet<Integer> availableIndices = new TreeSet<>(indices);
        for(Position p : queens.keySet()){
            availableIndices.remove(p.getSleepingQueenPosition().getCardIndex());
        }
        Position newPosition = new Position(new SleepingQueenPosition(availableIndices.first()));
        queens.put(newPosition, queen);
        return newPosition;
    }

    public Queen removeQueen(Position position){
        return queens.remove(position);
    }

    public Map<Position, Queen> getQueens(){
        return queens;
    }


}
