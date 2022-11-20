package Application.Entities.Accounts.AccountHandler;

import Application.Entities.ATM.ATM;
import Application.Entities.Accounts.BankAccount;
import Application.Entities.Builders.CardBuilder;
import Application.Entities.Builders.VisaCardBuilder.SixteenDigitCardBuilder;
import Application.Entities.Cards.Card;
import Application.Entities.DataBaseOperations.CardsDBOperation.CardsDBOperation;
import Application.Entities.Directors.CardDirector.Director;
import Application.Entities.Utilities.IOC.IOC;

import java.util.InputMismatchException;

import static Application.Entities.Constants.Constants.*;

public class AccountHandler {

    static CardsDBOperation operations = CardsDBOperation.getInstance();
    static ATM atm = ATM.getInstance();


    public static Card login(String cardNum) {

        var card = identification(cardNum);

        if(card == null || card.isBlocked()) return null;

        int attemptsLeft = Integer.valueOf(MENU_ATTEMPTS_LEFT);

        String password = IOC.getString(ENTER + CARD_PASS);

        while(attemptsLeft > 0 && !card.getPassword().equals(password)) {

            attemptsLeft--;
            IOC.writeMessage(MENU_LOGIN_ERROR);
            IOC.writeMessage(MENU_ATTEMPTS_LEFT_MSG + attemptsLeft );
            password = IOC.getString(ENTER + CARD_PASS);

        }

        if(attemptsLeft == 0 && !card.getPassword().equals(password)) {

            IOC.writeMessage(CARD_IS_BLOCKED);

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

        Director director = new Director();
        CardBuilder builder = new SixteenDigitCardBuilder();
        director.setCardBuilder(builder);
        var card = director.createCard();
        bankAccount.addCard(card);

        operations.insert(card);

        IOC.writeMessage(CARD_NUM + card.getNumber());
        IOC.writeMessage(CARD_PASS + card.getPassword());

        return card;

    }

    public static void selectOption(Card card) {

        var atmBalance = atm.getBalance();
        var cardBalance = card.getAccount().getBalance();

        while(true) {

            int choice = 0;
            try {
                choice = IOC.getInt(MENU_SELECT_OPTION + "\n" + MENU_OPTION_MENU + "\n" + ENTER);
            }
            catch(InputMismatchException ex) {
                IOC.writeMessage(MENU_INCORRECT_INPUT);
                IOC.scanner.next();
            }

            switch(choice) {

                case 1:

                    IOC.writeMessage(CARD_BALANCE + cardBalance);

                    break;

                case 2:

                    Long depositSum = 0L;

                    while(depositSum == 0) {

                        try {
                            depositSum = Long.valueOf(IOC.getString(CARD_DEPOSIT_SUM));
                        }
                        catch(NumberFormatException ex) {
                            IOC.writeMessage(MENU_INCORRECT_INPUT);
                            IOC.scanner.next();
                        }

                        while(depositSum > Long.valueOf(ATM_DEPOSIT_LIMIT)) {
                            IOC.writeMessage(ATM_LARGE_AMOUNT);
                            depositSum = Long.valueOf(IOC.getString(CARD_DEPOSIT_SUM));
                        }

                        cardBalance += depositSum;
                        card.getAccount().setBalance(cardBalance);
                        atm.fillUp(depositSum);
                    }

                    break;

                case 3:

                    var cashOutSum = Long.valueOf(IOC.getString(MENU_CASH_OUT));

                    while(atmBalance < cashOutSum || cardBalance < cashOutSum || cashOutSum < 0) {

                        if(atmBalance < cashOutSum)
                            IOC.writeMessage(ATM_NOT_ENOUGH_MONEY + "\t" + CARD_BALANCE + atmBalance);

                        if(cardBalance < cashOutSum)
                            IOC.writeMessage(CARD_BALANCE + "\t" + CARD_BALANCE + cardBalance);


                        cashOutSum = Long.valueOf(IOC.getString(MENU_CASH_OUT));
                    }
                    cardBalance -= cashOutSum;
                    atmBalance -= cashOutSum;
                    atm.setBalance(atmBalance);
                    card.getAccount().setBalance(cardBalance);

                    break;

                case 4:
                    return;

            }

        }

    }

}
