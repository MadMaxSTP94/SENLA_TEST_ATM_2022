package Application.Entities.Directors.CardDirector;

import Application.Entities.Builders.CardBuilder;
import Application.Entities.Cards.Card;

public class Director {

    CardBuilder cardBuilder;

    public void setCardBuilder(CardBuilder cardBuilder) {
        this.cardBuilder = cardBuilder;
    }

    public Card createCard() {
        cardBuilder.createCard();
        cardBuilder.buildNumber();
        cardBuilder.buildPassword();
        cardBuilder.buildCardType();

        return cardBuilder.getCard();
    }

}
