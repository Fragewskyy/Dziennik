package config;

import controller.Action;
import controller.PrincipalActions.*;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import s.ConsoleView;
import s.Menu;

import java.util.Arrays;
import java.util.List;

public class PrincipalConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        System.out.println("Logged as principal.");
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction()
                ,new CreateNewClassAndAssignToTeacherAction(),new DisplayyAllPersonsAction(),new ExecuteClassesAndStudentsAction(),new SetNumberOffLessonsAction(),new SetUpTeacherAction(),new DisplayAllTeachers()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
