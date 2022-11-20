package Application.Entities.Constants;

import java.io.FileInputStream;
import java.util.Properties;

public class Constants {

    public static String FILE_PATH;
    public static String FILE_RECORDING_COMPLETE;

    public static String MENU_ATTEMPTS_LEFT;
    public static String MENU_ATTEMPTS_LEFT_MSG;
    public static String MENU_INCORRECT_INPUT;
    public static String MENU_SELECT_OPTION;
    public static String MENU_LOGIN_SUCCESS;
    public static String MENU_SIGNUP_SUCCESS;
    public static String MENU_LOGIN_ERROR;
    public static String MENU_SHOW_BALANCE;
    public static String MENU_MAKE_DEPOSIT;
    public static String MENU_MAIN_MENU;
    public static String MENU_OPTION_MENU;
    public static String MENU_CONTINUE_OR_EXIT;
    public static String MENU_CASH_OUT;

    public static String CARD_NUM;
    public static String CARD_PASS;
    public static String CARD_BALANCE;
    public static String CARD_DEPOSIT_SUM;
    public static String CARD_NOT_EXIST;
    public static String CARD_IS_BLOCKED;

    public static String ENTER;

    public static String ATM_CASH;
    public static String ATM_NOT_ENOUGH_MONEY;
    public static String ATM_DEPOSIT_LIMIT;
    public static String ATM_DATE_FORMAT;
    public static String ATM_LARGE_AMOUNT;

    public static int BANK_ACCOUNT_CURRENT_LENGTH;
    public static int VISA_CARD_NUMBER_LENGTH;
    public static int CARD_PASSWORD_LENGTH;


    static {

        FileInputStream fis;
        Properties property = new Properties();

        try {

            fis = new FileInputStream("src/resources/strings.properties");
            property.load(fis);

            FILE_PATH = property.getProperty("File.FILE_PATH");
            FILE_RECORDING_COMPLETE = property.getProperty("File.recording_complete");

            MENU_CONTINUE_OR_EXIT = property.getProperty("Menu.continue_or_exit");
            MENU_ATTEMPTS_LEFT = property.getProperty("Menu.attempts_left");
            MENU_ATTEMPTS_LEFT_MSG = property.getProperty("Menu.attempts_left_msg");
            MENU_SELECT_OPTION = property.getProperty("Menu.select_option");
            MENU_LOGIN_SUCCESS = property.getProperty("Menu.login_success");
            MENU_SIGNUP_SUCCESS = property.getProperty("Menu.signup_success");
            MENU_LOGIN_ERROR = property.getProperty("Menu.login_error");
            MENU_SHOW_BALANCE = property.getProperty("Menu.show_balance");
            MENU_MAKE_DEPOSIT = property.getProperty("Menu.make_deposit");
            MENU_CASH_OUT = property.getProperty("Menu.cash_out");
            MENU_MAIN_MENU = property.getProperty("Menu.main_menu");
            MENU_OPTION_MENU = property.getProperty("Menu.option_menu");
            MENU_INCORRECT_INPUT = property.getProperty("Menu.incorrect_input");

            CARD_DEPOSIT_SUM = property.getProperty("Card.deposit_sum");
            CARD_NUM = property.getProperty("Card.num");
            CARD_PASS = property.getProperty("Card.pass");
            CARD_BALANCE = property.getProperty("Card.balance");
            CARD_NOT_EXIST = property.getProperty("Card.not_exist");
            CARD_IS_BLOCKED = property.getProperty("Card.card_blocked");

            ATM_CASH = property.getProperty("ATM.cash");
            ATM_LARGE_AMOUNT = property.getProperty("ATM.large_amount");
            ATM_NOT_ENOUGH_MONEY = property.getProperty("ATM.not_enough_money");
            ATM_DEPOSIT_LIMIT = property.getProperty("ATM.deposit_limit");
            ATM_DATE_FORMAT = property.getProperty("ATM.date_format");

            ENTER = property.getProperty("Actions.enter");



        } catch (Exception e) {

            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    static {

        BANK_ACCOUNT_CURRENT_LENGTH = 20;
        VISA_CARD_NUMBER_LENGTH = 16;
        CARD_PASSWORD_LENGTH = 4;

    }

}
