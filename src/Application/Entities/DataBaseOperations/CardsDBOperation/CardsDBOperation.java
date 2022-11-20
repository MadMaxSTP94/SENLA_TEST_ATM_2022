package Application.Entities.DataBaseOperations.CardsDBOperation;

import Application.Data.Database.CardsDatabase;
import Application.Data.FileManagement.FileWriter;
import Application.Entities.Cards.Card;
import Application.Entities.Constants.Constants;
import Application.Entities.DataBaseOperations.DBOperations.FindOperation;
import Application.Entities.DataBaseOperations.DBOperations.InsertOperation;
import Application.Entities.DataBaseOperations.DBOperations.RemoveOperation;
import Application.Entities.DataBaseOperations.DBOperations.SaveOperation;

public final class CardsDBOperation implements FindOperation, InsertOperation, RemoveOperation, SaveOperation {

    private static CardsDatabase cardsDatabase;
    private static CardsDBOperation instance;

    private CardsDBOperation() {
        cardsDatabase = CardsDatabase.getInstance();
    }

    public static CardsDBOperation getInstance() {
        return instance == null ? new CardsDBOperation() : instance;
    }

    @Override
    public Object find(Object cardNumber) {
        boolean isIn = cardsDatabase.cards.stream().anyMatch(card -> card.getNumber().equals((String) cardNumber));
        if(!isIn) return null;
        return cardsDatabase.cards.stream().filter(card -> card.getNumber().equals((String) cardNumber)).findFirst().get();
    }

    @Override
    public void insert(Object card) {
        cardsDatabase.cards.add((Card) card);
    }

    @Override
    public void remove(Object card) {
        cardsDatabase.cards.remove((Card) card);
    }

    @Override
    public void save() {
        FileWriter.write(Constants.FILE_PATH,cardsDatabase.cards);
    }

}
