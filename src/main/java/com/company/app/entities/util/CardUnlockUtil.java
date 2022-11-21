package main.java.com.company.app.entities.util;

import main.java.com.company.app.entities.card.Card;

import java.util.Calendar;
import java.util.List;


public class CardUnlockUtil {

    private static CardUnlockUtil instance;

    private CardUnlockUtil() { }

    public static CardUnlockUtil getInstance() {
        if(instance == null) {
            instance = new CardUnlockUtil();
        }
        return instance;
    }

    public void unlockCard(Card card) {
        if(card == null || card.getBlockTime() == null) return;
        if(card.getBlockTime().before(Calendar.getInstance())) {
            card.setBlocked(false);
            card.setBlockTime(null);
        }
    }

    public void unlockCards(List<Card> cards) {
        for(var card : cards)
            unlockCard(card);
    }

}
