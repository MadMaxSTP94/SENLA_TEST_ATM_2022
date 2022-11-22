package main.java.com.company.app.entities.bankAccount;

import main.java.com.company.app.entities.atm.ATM;
import main.java.com.company.app.entities.card.builders.CardBuilder;
import main.java.com.company.app.entities.card.builders.VisaCardBuilder.SixteenDigitCardBuilder;
import main.java.com.company.app.entities.card.Card;
import main.java.com.company.app.entities.dbService.impl.CardsDBService;
import main.java.com.company.app.entities.card.cardDirector.CardDirector;
import main.java.com.company.app.entities.util.CardUnlockUtil;
import main.java.com.company.app.entities.util.IOCUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

import static main.java.com.company.app.entities.util.Constants.*;

public class BankAccountService {

    static CardsDBService operations = CardsDBService.getInstance();
    static ATM atm = ATM.getInstance();
    public static BankAccountService instance;
    static CardUnlockUtil cardUnlockUtil = CardUnlockUtil.getInstance();

    private BankAccountService() {}

    public static BankAccountService getInstance() {
        if(instance == null){
            instance = new BankAccountService();
        }
        return instance;
    }

    public synchronized Card login(String cardNum) {

        var card = identification(cardNum);
        cardUnlockUtil.unlockCard(card);
        if(card == null || card.isBlocked()) return null;

        int attemptsLeft = Integer.valueOf(MENU_ATTEMPTS_LEFT);
        String password = IOCUtil.getString(ENTER + CARD_PASS);
        attemptsLeft--;

        while(attemptsLeft > 0 && !card.getPassword().equals(password)) {
            IOCUtil.writeMessage(MENU_LOGIN_ERROR);
            IOCUtil.writeMessage(MENU_ATTEMPTS_LEFT_MSG + attemptsLeft );
            password = IOCUtil.getString(ENTER + CARD_PASS);
            attemptsLeft--;

        }

        if(attemptsLeft == 0 && !card.getPassword().equals(password)) {

            Calendar cl = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(ATM_DATE_FORMAT);
            cl.add(Calendar.DAY_OF_MONTH,1);

            card.setBlocked(true);
            card.setBlockTime(cl);
            IOCUtil.writeMessage(CARD_IS_BLOCKED + "\t" + sdf.format(cl.getTime()));

            return null;

        }
        return card;

    }

    private static Card identification(String cardNum) {
        var card = (Card) operations.find(cardNum);
        if(card == null) return null;
        return card;
    }

    public static Card signIn() {
        BankAccount bankAccount = new BankAccount();

        CardDirector cardDirector = new CardDirector();
        CardBuilder builder = new SixteenDigitCardBuilder();
        cardDirector.setCardBuilder(builder);
        var card = cardDirector.createCard();
        bankAccount.addCard(card);

        operations.insert(card);

        IOCUtil.writeMessage(CARD_NUM + card.getNumber());
        IOCUtil.writeMessage(CARD_PASS + card.getPassword());

        return card;

    }

    public static void selectOption(Card card) {
        var atmBalance = atm.getBalance();
        var cardBalance = card.getAccount().getBalance();

        while(true) {

            int choice = 0;
            try {
                choice = IOCUtil.getInt(MENU_SELECT_OPTION + "\n" + MENU_OPTION_MENU + "\n" + ENTER);
            }
            catch(InputMismatchException ex) {
                IOCUtil.writeMessage(MENU_INCORRECT_INPUT);
                IOCUtil.scanner.next();
            }

            switch(choice) {

                case 1:
                    IOCUtil.writeMessage(CARD_BALANCE + cardBalance);
                    break;

                case 2:
                    Long depositSum = -1L;

                    while(depositSum == -1) {
                        try {
                            depositSum = Long.valueOf(IOCUtil.getString(CARD_DEPOSIT_SUM));
                        }
                        catch(NumberFormatException ex) {
                            IOCUtil.writeMessage(MENU_INCORRECT_INPUT);
                            IOCUtil.scanner.nextLine();
                        }

                        while(depositSum > Long.valueOf(ATM_DEPOSIT_LIMIT)) {
                            IOCUtil.writeMessage(ATM_LARGE_AMOUNT);
                            depositSum = Long.valueOf(IOCUtil.getString(CARD_DEPOSIT_SUM));
                        }

                        if(depositSum > 0) {
                            cardBalance += depositSum;
                            card.getAccount().setBalance(cardBalance);
                            atm.fillUp(depositSum);
                        }
                        else {
                            IOCUtil.writeMessage(MENU_INCORRECT_INPUT);
                        }

                    }
                    break;

                case 3:
                    Long cashOutSum = -1L;
                    boolean isDone = false;

                    while(!isDone) {
                        try{
                            cashOutSum = Long.valueOf(IOCUtil.getString(MENU_CASH_OUT));

                            while(atmBalance < cashOutSum || cardBalance < cashOutSum || cashOutSum < 0) {

                                if(atmBalance < cashOutSum)
                                    IOCUtil.writeMessage(ATM_NOT_ENOUGH_MONEY + cardBalance + "\t" + CARD_BALANCE + atmBalance);

                                if(cardBalance < cashOutSum)
                                    IOCUtil.writeMessage(CARD_BALANCE + cardBalance);

                                cashOutSum = Long.valueOf(IOCUtil.getString(MENU_CASH_OUT));

                            }
                            cardBalance -= cashOutSum;
                            atmBalance -= cashOutSum;
                            atm.setBalance(atmBalance);
                            card.getAccount().setBalance(cardBalance);
                            isDone = true;
                        }
                        catch(NumberFormatException ex) {
                            IOCUtil.writeMessage(MENU_INCORRECT_INPUT);
                        }
                    }

                    break;

                case 4:
                    return;

            }

        }

    }

}
