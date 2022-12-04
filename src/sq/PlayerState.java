package sq;

import java.util.Map;
import java.util.Optional;

public class PlayerState {
    private Map<Integer, Optional<Card>> cards;
    private Map<Integer, Queen> awokenQueens;
}
