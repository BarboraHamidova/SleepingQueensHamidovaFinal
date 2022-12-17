package sq;

public class Card {
    public CardType tyoe;
    public final Integer value;

    public Card(CardType type, Integer value){
        this.tyoe = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Card card = (Card) o;
            return this.tyoe == card.tyoe && this.value == card.value;
        } else {
            return false;
        }
    }
}
