package sq;

import java.util.HashMap;
import java.util.Map;

public class AwokenQueens {
    Map<Position, Queen> queens;

    public AwokenQueens(){
        queens = new HashMap<>();
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
