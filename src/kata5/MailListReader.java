package kata5;
import java.util.*;
import java.io.*;

public class MailListReader {
	
    public static List<String> read(String fileName){
        List<String> mailList = new ArrayList<>();
        String mail;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            while ((mail = reader.readLine()) != null){
                if(mail.contains("@")) mailList.add(mail);
            }
            reader.close();
        } catch(FileNotFoundException fnfe){
            System.out.println("Fichero no encontrado " + fnfe);
        } catch (IOException io){
            System.out.println("Error entrada y salida " + io);
        }
        return mailList;
    }
}
