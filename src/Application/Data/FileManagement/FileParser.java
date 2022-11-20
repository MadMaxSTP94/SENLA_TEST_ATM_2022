package Application.Data.FileManagement;

import Application.Entities.Cards.Card;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<Card> parse(String PATH) {

        List<Card> cards = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH)))
        {
            cards = ((ArrayList<Card>)ois.readObject());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return cards;
    }

}
