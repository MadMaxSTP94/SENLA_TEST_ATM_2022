package Application.Data.FileManagement;

import Application.Entities.Cards.Card;
import Application.Entities.Constants.Constants;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileWriter {

    public static void write(String PATH, List<Card> cards){

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH)))
        {
            oos.writeObject(cards);
            System.out.println(Constants.FILE_RECORDING_COMPLETE);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
