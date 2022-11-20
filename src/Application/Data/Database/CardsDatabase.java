package Application.Data.Database;

import Application.Data.FileManagement.FileParser;
import Application.Entities.Cards.Card;
import Application.Entities.Constants.Constants;

import java.util.List;

public final class CardsDatabase {

    private static CardsDatabase instance;
    public List<Card> cards;

    private CardsDatabase() {

        try {

            cards = FileParser.parse(Constants.FILE_PATH);

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
