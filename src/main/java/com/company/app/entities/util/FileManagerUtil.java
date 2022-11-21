package main.java.com.company.app.entities.util;

import main.java.com.company.app.entities.card.Card;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManagerUtil {

    public static List<Card> parse(String PATH) {
        List<Card> cards = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            cards = ((ArrayList<Card>)ois.readObject());
        }
        catch (Exception ex){
            IOCUtil.writeMessage(ex.getMessage());
        }

        return cards;
    }

    public static void write(String PATH, List<Card> cards){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            oos.writeObject(cards);
            System.out.println(Constants.FILE_RECORDING_COMPLETE);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

}
