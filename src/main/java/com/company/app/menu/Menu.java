package main.java.com.company.app.menu;

import main.java.com.company.app.entities.card.CardsDatabase;
import main.java.com.company.app.entities.atm.ATM;
import main.java.com.company.app.entities.bankAccount.BankAccountService;
import main.java.com.company.app.entities.card.Card;
import main.java.com.company.app.entities.dbService.impl.CardsDBService;
import main.java.com.company.app.entities.util.CardUnlockUtil;
import static main.java.com.company.app.entities.util.IOCUtil.*;

import static main.java.com.company.app.entities.util.Constants.*;
import static main.java.com.company.app.entities.util.Constants.MENU_INCORRECT_INPUT;

public class Menu {

    public static void start(){

        CardsDatabase base = CardsDatabase.getInstance();
        var cardsBase = base.cards;
        CardUnlockUtil.getInstance().unlockCards(cardsBase);
        ATM atm = ATM.getInstance();
        atm.fillUp(3000L);
        atm.setDateFormat(ATM_DATE_FORMAT);
        Card card;
        BankAccountService bankAccountService = BankAccountService.getInstance();

        for(var temp : cardsBase){
            writeMessage(temp.getNumber() + "\t" + temp.getPassword() + "\t" + temp.isBlocked());
        }

       while(true) {

            card = null;
            int choice = getInt(MENU_SELECT_OPTION + "\n" + MENU_MAIN_MENU + "\n" + ENTER);

            switch(choice) {

                case 1:
                    while(card == null) {

                        String cardNum = getString(ENTER + CARD_NUM);
                        card = bankAccountService.login(cardNum);

                        if (card != null) {
                            writeMessage(MENU_LOGIN_SUCCESS);
                            BankAccountService.selectOption(card);
                        }
                        else {
                            choice = getInt(MENU_INCORRECT_INPUT + "\n" + MENU_SELECT_OPTION + "\n" + MENU_CONTINUE_OR_EXIT + "\n" + ENTER);
                            while(choice < 0 || choice > 2) {
                                choice = getInt(MENU_INCORRECT_INPUT + "\n" + MENU_SELECT_OPTION + "\n" + MENU_CONTINUE_OR_EXIT + "\n" + ENTER);

                            }
                        }
                        if(choice == 2) break;
                    }

                    break;

                case 2:

                    writeMessage(MENU_SIGNUP_SUCCESS);
                    card = BankAccountService.signIn();
                    BankAccountService.selectOption(card);

                    break;

                case 3:
                    CardsDBService.getInstance().save();
                    System.exit(0);
                    break;

                default:
                    writeMessage(MENU_INCORRECT_INPUT);
                    break;
            }
        }
    }

}
