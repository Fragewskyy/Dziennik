
import config.Config;
import config.Configchanger;

import view.MainView;
import view.Menu;
import controller.Action;


import java.sql.*;
import java.util.List;

public class Main {
    public static String login;
    public static String password;

    public static String getlogin(){
        return login;
    }
    public static String getpassword(){
        return password;
    }

    public static void main(String[] args) throws SQLException {

        MainView.sampletext();
        login= MainView.readlogin();
        password=MainView.readPassword();
        Config config= Configchanger.inicializeConfig(login);
        List<Action> actions= config.inicializactions();
        while(true) {
            Menu menu = config.inicializemenu();
            menu.show(actions);
            Action action = menu.getChoice(actions);
            action.executeQuery();
        }




    }



}

