package config;

import controller.Action;

import controller.UserActions.ChangePasswordAction;
import controller.UserActions.InboxAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import controller.studentActions.DisplayAllGradesAction;
import controller.studentActions.ShowAllAbsenceAction;
import s.ConsoleView;
import s.Menu;

import java.util.Arrays;
import java.util.List;

public class StudentConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        System.out.println("Logged as student.");
        Action helper[]=new Action[] {new ChangePasswordAction(),
                new LogOutAction(),new SendTextMessegeAction(),new InboxAction(),new DisplayAllGradesAction(),new ShowAllAbsenceAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
