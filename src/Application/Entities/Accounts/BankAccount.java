package Application.Entities.Accounts;

import Application.Entities.Cards.Card;
import Application.Entities.Generators.NumGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static Application.Entities.Constants.Constants.*;

public class BankAccount implements Serializable {

    private String accountNum;
    private long balance;
    private List<Card> cards;

    public BankAccount() {
        accountNum = NumGenerator.generateFixLengthNumber(BANK_ACCOUNT_CURRENT_LENGTH);
        cards = new ArrayList<>();
    }

    public String getAccountNum() {
        return accountNum;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void addCard(Card card) {

        if(cards != null) {
            cards.add(card);
            card.setAccount(this);
        }

    }









}
