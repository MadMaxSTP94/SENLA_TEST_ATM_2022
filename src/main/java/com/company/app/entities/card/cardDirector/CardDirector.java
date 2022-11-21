package main.java.com.company.app.entities.card.cardDirector;

import main.java.com.company.app.entities.card.builders.CardBuilder;
import main.java.com.company.app.entities.card.Card;

public class CardDirector {

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
