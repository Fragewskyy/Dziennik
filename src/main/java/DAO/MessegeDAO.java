package DAO;

import model.peoplesRoles.Messege;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MessegeDAO {
    public static String createmessege(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type login of message recipient; ");
        String recipientLogin = scanner.next();
        System.out.println("Type subject of message: ");
        String subject = scanner.next();
        System.out.println("Type message: ");
        String messageText = scanner.next();
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        Messege messege=new Messege(subject,messageText,modifiedDate,recipientLogin);
        return "INSERT INTO messages(message_subject, message_text, date, user_id) VALUES ('" + messege.getMessege_subject() + "', '" + messege.getMessege_text() + "', '" + messege.getDate()+ "', (SELECT user_id FROM users WHERE login = '" + messege.getLogin() + "'));" ;
    }

}
