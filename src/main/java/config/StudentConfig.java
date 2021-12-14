package config;

import controller.Action;

import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import controller.studentActions.DisplayAllGradesAction;
import controller.studentActions.ShowAllAbsenceAction;
import view.ConsoleView;
import view.Menu;

import java.util.Arrays;
import java.util.List;

public class StudentConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        Action helper[]=new Action[] {new ChangePasswordAction(),
                new LogOutAction(),new SendTextMessegeAction(),new DisplayAllGradesAction(),new ShowAllAbsenceAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
