package config;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.InboxAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import s.ConsoleView;
import s.Menu;

import java.util.Arrays;
import java.util.List;

public class AdminConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        System.out.println("Logged as admin.");
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction(),new InboxAction(),new TurnOffOnpasswordAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
