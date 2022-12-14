package sq;

import java.util.HashMap;
import java.util.Map;

public class TestingQueenCollection {
    public static void main(String[] args) {
        System.out.println(new Position(new HandPosition(0,0)).equals(new Position(new HandPosition(0, 0))));
        Map<Position, Card> positionCardMap = new HashMap<>();
        positionCardMap.put(new Position(new HandPosition(0, 0), new AwokenQueenPosition(0, 0)), new Card(CardType.Number, 11));
        Card card = positionCardMap.get(new Position(new HandPosition(0, 0), new AwokenQueenPosition(0, 0)));
        System.out.println(card.tyoe + " " + card.value);
        QueenCollection queenCollection = new QueenCollection(new SleepingQueens());
    }

}
