package sq;

import java.util.Scanner;

public class Tests {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Variant hry: ");
        Integer variant = scanner.nextInt();
        DrawingAndTrashPile pile = new DrawingAndTrashPile(5, variant);
        Game game = new Game(5, pile);
        GameObservable observable = new GameObservable();
        GameAdaptor gameAdaptor = new GameAdaptor(game, observable);
        observable.add(new GameObserverImplementation("moj observer"));
        for(int i = 0; i < 5; i++) {
            observable.addPlayer(i, new GameObserverImplementation("observer hraca "+(i+1)));
        }

    }
}

