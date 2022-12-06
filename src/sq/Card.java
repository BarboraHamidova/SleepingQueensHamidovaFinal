package sq;

public class Card {
    public CardType tyoe;
    public final Integer value;

    public Card(CardType type, Integer value){
        this.tyoe = type;
        this.value = value;
    }
}
