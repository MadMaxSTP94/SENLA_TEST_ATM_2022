package Application.Entities.Builders;

import Application.Entities.Cards.Card;

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
