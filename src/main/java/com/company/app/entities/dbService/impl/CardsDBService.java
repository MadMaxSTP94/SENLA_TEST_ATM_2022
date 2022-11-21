package main.java.com.company.app.entities.dbService.impl;

import main.java.com.company.app.entities.card.CardsDatabase;
import main.java.com.company.app.entities.util.FileManagerUtil;
import main.java.com.company.app.entities.card.Card;
import main.java.com.company.app.entities.util.Constants;
import main.java.com.company.app.entities.dbService.DBService;

public final class CardsDBService implements DBService {

    private static CardsDatabase cardsDatabase;
    private static CardsDBService instance;

    private CardsDBService() {
        cardsDatabase = CardsDatabase.getInstance();
    }

    public static CardsDBService getInstance() {
        return instance == null ? new CardsDBService() : instance;
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
        FileManagerUtil.write(Constants.FILE_PATH,cardsDatabase.cards);
    }

}
