package Application.Entities.Builders.VisaCardBuilder;

import Application.Entities.Builders.CardBuilder;
import Application.Entities.Cards.CardType.CardType;

import static Application.Entities.Generators.NumGenerator.*;
import static Application.Entities.Constants.Constants.*;

public class SixteenDigitCardBuilder extends CardBuilder {

    @Override
    public void buildNumber() {
        card.setNumber(generateFixLengthNumber(VISA_CARD_NUMBER_LENGTH));
    }

    @Override
    public void buildPassword() {
        card.setPassword(generateFixLengthNumber(CARD_PASSWORD_LENGTH));
    }

    @Override
    public void buildCardType() {
        card.setCardType(CardType.CARD_TYPE_16_DIGIT);
    }

}
