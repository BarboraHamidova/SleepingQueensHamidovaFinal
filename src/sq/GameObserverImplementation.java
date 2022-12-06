package sq;

public class GameObserverImplementation implements GameObserver {
    String name;

    public GameObserverImplementation(String name){
        this.name = name;
    }

    @Override
    public void notify(String message){
        System.out.println(name + ":\n" +message);
    }
}
