package Application.Menu;

import Application.Entities.ATM.ATM;
import Application.Entities.Accounts.AccountHandler.AccountHandler;
import Application.Entities.Cards.Card;
import Application.Entities.DataBaseOperations.CardsDBOperation.CardsDBOperation;
import Application.Entities.Utilities.IOC.IOC;

import static Application.Entities.Constants.Constants.*;
import static Application.Entities.Constants.Constants.MENU_INCORRECT_INPUT;

public class Menu {

    public static void start() {

        ATM atm = ATM.getInstance();
        atm.fillUp(3000L);
        atm.setDateFormat(ATM_DATE_FORMAT);
        Card card = null;

        while(true) {

            int choice = IOC.getInt(MENU_SELECT_OPTION + "\n" + MENU_MAIN_MENU + "\n" + ENTER);

            switch(choice) {

                case 1:

                    while(card == null) {

                        String cardNum = IOC.getString(ENTER + CARD_NUM);
                        card = AccountHandler.login(cardNum);

                        if (card != null) {

                            IOC.writeMessage(MENU_LOGIN_SUCCESS);
                            AccountHandler.selectOption(card);

                        }
                        else {

                            choice = IOC.getInt(MENU_INCORRECT_INPUT + "\n" + MENU_SELECT_OPTION + "\n" + MENU_CONTINUE_OR_EXIT + "\n" + ENTER);

                            while(choice < 0 || choice > 2) {

                                choice = IOC.getInt(MENU_INCORRECT_INPUT + "\n" + MENU_SELECT_OPTION + "\n" + MENU_CONTINUE_OR_EXIT + "\n" + ENTER);

                            }

                        }

                        if(choice == 2) break;

                    }

                    break;

                case 2:

                    IOC.writeMessage(MENU_SIGNUP_SUCCESS);
                    card = AccountHandler.signIn();
                    CardsDBOperation.getInstance().insert(card);
                    AccountHandler.selectOption(card);

                    break;

                case 3:

                    CardsDBOperation.getInstance().save();
                    System.exit(0);

                    break;

                default:

                    IOC.writeMessage(MENU_INCORRECT_INPUT);

                    break;
            }
        }
    }

}
