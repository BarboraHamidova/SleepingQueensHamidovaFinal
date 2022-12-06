package sq;

import sq.CardType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EvaluateAttack {
    private CardType defenseCardType;
    private Player defender;
    private Player offender;
    private Hand defenderHand;

    public EvaluateAttack(CardType offenceCardType, Player defender, Player offender){
        this.defender = defender;
        this.offender = offender;
        this.defenderHand = defender.getHand();
        if(offenceCardType == CardType.Knight) {
            this.defenseCardType = CardType.Dragon;
        }
        else if(offenceCardType == CardType.SleepingPotion){
            this.defenseCardType = CardType.MagicWand;
        }
        else {
            this.defenseCardType = null;
        }
    }

    public boolean play(Position targetQueen, Integer targetPlayerIdx){
        HandPosition defensePosition = defenderHand.hasCardOfType(defenseCardType);
        if(defensePosition != null){
            List<HandPosition> pickedCard = new ArrayList<>();
            pickedCard.add(defensePosition);
            defenderHand.pickCards(pickedCard);
            Map<HandPosition, Card> newCards = defenderHand.removePickedCardsAndRedraw();
            for(HandPosition h : newCards.keySet()){
                defender.getPlayerState().cards.put(h.getCardIndex(), Optional.of(newCards.get(h)));
            }
            return  false;
        }
        else {
            /*Potrebujem ho zbavit queeny to sure ako*/
            return true;
        }
    }


}
