package Application.Entities.ATM;

public class ATM {

    private static ATM instance;
    private static long balance;
    private static String dateFormat;

    private ATM() {}

    public static ATM getInstance() {

        if(instance == null){
            instance = new ATM();
        }
        return instance;
    }

    public long getBalance() {
        return balance;
    }

    public void fillUp(long sum) {
        balance += sum;
    }

    public void setBalance(long sum) {
        balance = sum;
    }

    public void setDateFormat(String strFormat) {
        dateFormat = strFormat;
    }


}
