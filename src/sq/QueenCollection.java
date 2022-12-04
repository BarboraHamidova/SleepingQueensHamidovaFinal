package sq;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class QueenCollection {
    private Set<Queen> set = new HashSet<>();

    public void addQueen(Queen queen){
        set.add(queen);
    }

    public Optional<Queen> removeQueen(Queen queen){
        /*Sounds simple no idea how to use optional*/
        return Optional.empty();
    }
}
