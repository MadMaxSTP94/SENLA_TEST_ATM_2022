package main.java.com.company.app.entities.card;

import main.java.com.company.app.entities.bankAccount.BankAccount;

import java.io.Serializable;
import java.util.Calendar;

public class Card implements Serializable {

    private String number;
    private String password;
    private BankAccount account;
    private boolean isBlocked;
    private CardType cardType;
    private Calendar blockTime;

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setBlockTime(Calendar blockTime) { this.blockTime = blockTime; }

    public BankAccount getAccount() {
        return isBlocked ? null :  account;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Calendar getBlockTime() { return blockTime; }

    public boolean isBlocked() {
        return isBlocked;
    }

}
