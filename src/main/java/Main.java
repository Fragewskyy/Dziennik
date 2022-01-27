
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

    public static String getLogin(){
        return login;
    }
    public static String getPassword(){
        return password;
    }

    public static void main(String[] args) throws SQLException {

        MainView.sampletext();


        Config config= Configchanger.inicializeConfig(MainView.getLogin());
        List<Action> actions= config.inicializactions();
        while(true) {
            Menu menu = config.inicializemenu();
            menu.show(actions);
            Action action = menu.getChoice(actions);
            action.executeQuery();
        }




    }
    //przykładowy guardian: vbussellk@uiuc.edu 5VD1X8uG7X
    //przykladowy principal: lbenettelli12@smugmug.com 6mJUIsofsLZm
    //przykladowy teacher: afowliez@microsoft.com omvKgRv
    //przykladowy student: mscreen3@jimdo.com MwxbdR7vmBS


}

