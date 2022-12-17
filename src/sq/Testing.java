package sq;

import javax.management.loading.ClassLoaderRepository;
import java.util.*;

public class Testing {

    public static boolean equalListsOfCards(List<Card> discarded, List<Card> allCards){
        if(discarded.size() != allCards.size()){
            return false;
        }
        for(Card c : discarded){
            allCards.remove(c);
        }
        if(allCards.isEmpty()){
            return true;
        }
        return false;
    }

    public static ArrayList<Card> removeUsedCards(ArrayList<Card> allCards, ArrayList<Card> newCards){
        for(Card c : newCards){
            if(allCards.contains(c)) {
                allCards.remove(c);
            }
        }
        return allCards;
    }

    public static void testingPile(DrawingAndTrashPile variant0, DrawingAndTrashPile variant1, List<Card> allCards){
        int success = 0;
        int numOfTests = 0;
        ArrayList<Card> notUsedCards = new ArrayList<>(allCards);
        ArrayList<Card> newCards = new ArrayList<>();
        newCards.addAll(variant0.initialDraw());
        notUsedCards = removeUsedCards(notUsedCards, newCards);
        for(int i = 1; i < 62; i++){
            ArrayList<Card> oldCards = new ArrayList<>(newCards);
            newCards = new ArrayList<>();
            newCards.addAll(variant0.discardAndDraw(oldCards));
            notUsedCards = removeUsedCards(notUsedCards, newCards);
        }
        numOfTests += 1;
        if(notUsedCards.isEmpty()){
            success += 1;
        }
        variant0.discardAndDraw(newCards);
        numOfTests += 1;
        if(equalListsOfCards(variant0.getCardsDiscardedThisTurn(), new ArrayList<>(allCards))){
            success += 1;
        }
        notUsedCards = new ArrayList<>(allCards);
        newCards = new ArrayList<>();
        newCards.addAll(variant1.initialDraw());
        notUsedCards = removeUsedCards(notUsedCards, newCards);
        for(int i = 3; i < 62; i+=3){
            ArrayList<Card> oldCards = new ArrayList<>(newCards);
            newCards = new ArrayList<>();
            newCards.addAll(variant1.discardAndDraw(oldCards));
            notUsedCards = removeUsedCards(notUsedCards, newCards);
        }
        numOfTests += 1;
        if(notUsedCards.isEmpty()){
            success += 1;
        }

        System.out.println("Testing DrawingAndTrashPile succeeded: " + success + " out of " + numOfTests + " tests");
    }

    public static void testingHand(Hand hand, List<HandPosition> positions){
        int success = 0;
        int numOfTests = 0;
        List<Card> cardsFromMap = new ArrayList<>();
        hand.pickCards(positions);
        Map<HandPosition, Card> mapOfCards = hand.removePickedCardsAndRedraw();
        for(HandPosition handPosition : mapOfCards.keySet()){
            cardsFromMap.add(mapOfCards.get(handPosition));
            numOfTests += 1;
            if(hand.hasCardOfType(mapOfCards.get(handPosition).tyoe) != null){
                success += 1;
            }
        }
        numOfTests += 1;
        if(equalListsOfCards(cardsFromMap, hand.getCards())){
            success += 1;
        }
        hand.pickCards(positions);
        numOfTests += 1;
        if(equalListsOfCards(cardsFromMap, hand.returnPickedCards())){
            success += 1;
        }

        System.out.println("Testing Hand succeeded: " + success + " out of " + numOfTests + " tests");

    }

    public static void testingEvaluateNumberedCards(Map<List<Card>, Boolean> listsOfCards){
        int success = 0;
        int numOfTests = 0;
        EvaluateNumberedCards eval = new EvaluateNumberedCards();
        for(List<Card> list : listsOfCards.keySet()){
            numOfTests += 1;
            if(eval.play(list) == listsOfCards.get(list)){
                success += 1;
            }
        }
        System.out.println("Testing EvaluateNumberedCards succeeded: " + success + " out of " + numOfTests + " tests");
    }

    public static void testingPlayer(Player kingsPlayer, Player knightPlayer, Player spPlayer, Player wandPlayer, Player dragonPlayer){
        int success = 0;
        int numOfTests = 0;
        List<Position> positions = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            positions.add(new Position(new HandPosition(i, 0), new SleepingQueenPosition(i)));
        }
        kingsPlayer.play(positions);
        PlayerState playerState = kingsPlayer.getPlayerState();
        numOfTests += 1;
        if(playerState.awokenQueens.size() == 5){
            success += 1;
        }
        positions = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            positions.add(new Position(new HandPosition(i, 1), new AwokenQueenPosition(i, 0)));
        }
        knightPlayer.play(positions);
        playerState = knightPlayer.getPlayerState();
        numOfTests += 1;
        if(playerState.awokenQueens.size() == 5 && kingsPlayer.getPlayerState().awokenQueens.isEmpty()){
            success += 1;
        }

        positions = new ArrayList<>();
        positions.add(new Position(new HandPosition(4, 3), new SleepingQueenPosition(5)));
        wandPlayer.play(positions);
        numOfTests += 1;
        if(wandPlayer.getPlayerState().awokenQueens.size() == 1){
            success += 1;
        }

        positions = new ArrayList<>();
        positions.add(new Position(new HandPosition(0, 2), new AwokenQueenPosition(0, 3)));
        positions.add(new Position(new HandPosition(0, 2), new AwokenQueenPosition(0, 1)));
        spPlayer.play(positions);
        numOfTests += 1;
        if(wandPlayer.getPlayerState().awokenQueens.size() == 1){
            success += 1;
        }
        numOfTests += 1;
        if(knightPlayer.getPlayerState().awokenQueens.size() == 4){
            success += 1;
        }

        positions = new ArrayList<>();
        positions.add(new Position(new HandPosition(4, 4), new SleepingQueenPosition(6)));
        dragonPlayer.play(positions);
        numOfTests += 1;
        if(dragonPlayer.getPlayerState().awokenQueens.size() == 1){
            success += 1;
        }

        positions = new ArrayList<>();
        positions.add(new Position(new HandPosition(0, 1), new AwokenQueenPosition(0, 4)));
        knightPlayer.play(positions);
        numOfTests += 1;
        if(dragonPlayer.getPlayerState().awokenQueens.size() == 1){
            success += 1;
        }

        System.out.println("Testing Player succeeded: " + success + " out of " + numOfTests + " tests");

    }

    public static void main(String[] args) {
        DrawingAndTrashPile variant0 = new DrawingAndTrashPile(1, 0);
        DrawingAndTrashPile variant1 = new DrawingAndTrashPile(3, 1);
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            cards.add(new Card(CardType.King, 0));
        }
        for(int i = 0; i < 4; i++){
            cards.add(new Card(CardType.Knight, 0));
            cards.add(new Card(CardType.SleepingPotion, 0));
        }
        for(int i = 0; i < 3; i++){
            cards.add(new Card(CardType.Dragon, 0));
            cards.add(new Card(CardType.MagicWand, 0));
        }
        for(int i = 1; i <= 10; i++){
            for(int j = 0; j < 4; j++){
                cards.add(new Card(CardType.Number, i));
            }
        }
        testingPile(variant0, variant1, cards);

        Hand hand = new Hand(new DrawingAndTrashPile(5, 0), 0);
        List<HandPosition> positions = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            positions.add(new HandPosition(i, 0));
        }
        testingHand(hand, positions);

        Map<List<Card>, Boolean> map = new HashMap<>();
        ArrayList<Card> a1 = new ArrayList<>();
        ArrayList<Card> a2 = new ArrayList<>();
        ArrayList<Card> a3 = new ArrayList<>();
        ArrayList<Card> a4 = new ArrayList<>();
        ArrayList<Card> a5 = new ArrayList<>();
        ArrayList<Card> a6 = new ArrayList<>();
        a1.add(new Card(CardType.Number, 7));
        map.put(a1, true);
        a2.add(new Card(CardType.Number, 9));
        a2.add(new Card(CardType.Number, 9));
        map.put(a2, true);
        a3.add(new Card(CardType.Number, 9));
        a3.add(new Card(CardType.Number, 1));
        map.put(a3, false);
        a4.add(new Card(CardType.Number, 9));
        for(int i = 0; i < 9; i++){
            a4.add(new Card(CardType.Number, 1));
        }
        map.put(a4, true);
        a5.add(new Card(CardType.Number, 6));
        a5.add(new Card(CardType.Number, 4));
        a5.add(new Card(CardType.Number, 3));
        map.put(a5, false);
        a6.add(new Card(CardType.Number, 6));
        a6.add(new Card(CardType.Number, 4));
        a6.add(new Card(CardType.Number, 2));
        map.put(a6, true);
        testingEvaluateNumberedCards(map);

        LinkedList<Card> kings = new LinkedList<>();
        LinkedList<Card> knights = new LinkedList<>();
        LinkedList<Card> sleepingPotions = new LinkedList<>();
        LinkedList<Card> wands = new LinkedList<>();
        LinkedList<Card> dragons = new LinkedList<>();
        for(int i = 0; i < 8; i++){
            kings.add(new Card(CardType.King, 0));
            knights.add(new Card(CardType.Knight, 0));
            sleepingPotions.add(new Card(CardType.SleepingPotion, 0));
            wands.add(new Card(CardType.MagicWand, 0));
            dragons.add(new Card(CardType.Dragon, 0));
        }
        wands.addFirst(new Card(CardType.King, 0));
        dragons.addFirst(new Card(CardType.King, 0));
        DrawingAndTrashPile kingsPile = new DrawingAndTrashPile(5, 0, kings);
        DrawingAndTrashPile knightsPile = new DrawingAndTrashPile(5, 0, knights);
        DrawingAndTrashPile sleepingPotionsPile = new DrawingAndTrashPile(5, 0, sleepingPotions);
        DrawingAndTrashPile wandsPile = new DrawingAndTrashPile(5, 0, wands);
        DrawingAndTrashPile dragonsPile = new DrawingAndTrashPile(5, 0, dragons);
        Game mockGame = new Game(5);
        Player kingsPlayer = new Player(0, kingsPile, mockGame, mockGame.getQueenCollection());
        Player knightPlayer = new Player(1, knightsPile, mockGame, mockGame.getQueenCollection());
        Player spPlayer = new Player(2, sleepingPotionsPile, mockGame, mockGame.getQueenCollection());
        Player wandsPlayer = new Player(3, wandsPile, mockGame, mockGame.getQueenCollection());
        Player dragonPlayer = new Player(4, dragonsPile, mockGame, mockGame.getQueenCollection());
        mockGame.addPlayer(kingsPlayer);
        mockGame.addPlayer(knightPlayer);
        mockGame.addPlayer(spPlayer);
        mockGame.addPlayer(wandsPlayer);
        mockGame.addPlayer(dragonPlayer);
        testingPlayer(kingsPlayer, knightPlayer, spPlayer, wandsPlayer, dragonPlayer);

    }
}
