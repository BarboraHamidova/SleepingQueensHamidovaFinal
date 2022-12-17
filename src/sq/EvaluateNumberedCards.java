package sq;

import java.util.List;

public class EvaluateNumberedCards {

    private Integer findMax(List<Card> cards){
        Integer max = cards.get(0).value;
        for(Card c : cards){
            if(max <= c.value){
                max = c.value;
            }
        }
        return max;
    }

    private Integer findSum(List<Card> cards){
        int sum = 0;
        for(Card c : cards){
            sum += c.value;
        }
        return sum;
    }

    public boolean play(List<Card> cards){
        if(cards.size() == 1){
            return true;
        }
        else if(cards.size() == 2 && cards.get(0).value == cards.get(1).value){
            return true;
        }
        else if(cards.size() > 2) {
            int expSum = 2 * findMax(cards);
            if(expSum == findSum(cards)){
                return true;
            }
        }
        return false;
    }
}
