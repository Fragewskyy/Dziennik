package config;

import controller.Action;
import controller.AdminActions.TurnOffOnpasswordAction;
import controller.PrincipalActions.*;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;

import java.util.Arrays;
import java.util.List;

public class PrincipalConfig implements Config{
    @Override
    public List<Action> inicializactions() {
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction()
                ,new CreateNewClassAndAssignToTeacherAction(),new DisplayyAllPersonsAction(),new ExecuteClassesAndStudentsAction(),new SetNumberOffLessonsAction(),new SetUpTeacherAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return null;
    }
}
