package main.java.com.company.app.entities.card.builders.VisaCardBuilder;

import main.java.com.company.app.entities.card.builders.CardBuilder;
import main.java.com.company.app.entities.card.CardType;

import static main.java.com.company.app.entities.util.NumGeneratorUtil.*;
import static main.java.com.company.app.entities.util.Constants.*;

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
