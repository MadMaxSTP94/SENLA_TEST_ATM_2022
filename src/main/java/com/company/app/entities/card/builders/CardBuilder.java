package main.java.com.company.app.entities.card.builders;

import main.java.com.company.app.entities.card.Card;

public abstract class CardBuilder {

    protected Card card;

    public abstract void buildNumber();
    public abstract void buildPassword();
    public abstract void buildCardType();

    public void createCard() {
        card = new Card();
    }

    public Card getCard() {
        return card;
    }

}
