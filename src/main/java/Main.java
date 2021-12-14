
import config.Config;
import config.Configchanger;

import config.Menu;
import controller.Action;
import view.MianVeiw;


import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {
        MianVeiw mianVeiw=new MianVeiw();
        mianVeiw.sampletext();
        String login= mianVeiw.readLogin();
        String password= mianVeiw.readPasword();
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

