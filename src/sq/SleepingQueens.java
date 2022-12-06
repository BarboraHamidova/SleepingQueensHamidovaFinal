package sq;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SleepingQueens {
    Map<Position, Queen> queens;

    public SleepingQueens(){
        for (int i = 0; i < 12; i++){
            Queen queen = new Queen();
            Position position = new Position(new SleepingQueenPosition(i));
            queens.put(position, queen);
        }
    }

    public void addQueen(Position position, Queen queen){
        queens.put(position, queen);
    }

    public Queen removeQueen(Position position){
        return queens.remove(position);
    }

    public Map<Position, Queen> getQueens(){
        return queens;
    }


}
