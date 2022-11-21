package main.java.com.company.app.entities.card;

import main.java.com.company.app.entities.util.FileManagerUtil;
import main.java.com.company.app.entities.util.Constants;

import java.util.List;

public final class CardsDatabase {

    private static CardsDatabase instance;
    public List<Card> cards;

    private CardsDatabase() {
        try {
            cards = FileManagerUtil.parse(Constants.FILE_PATH);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static CardsDatabase getInstance() {
        if(instance == null) {
            instance = new CardsDatabase();
        }
        return instance;
    }

}
