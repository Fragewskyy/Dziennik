package config;

import controller.Action;
import controller.GuardianActions.*;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import s.ConsoleView;
import s.Menu;

import java.util.Arrays;
import java.util.List;

public class GuardianConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        System.out.println("Logged as guardian.");
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction()
                ,new DemonstrateIfmystudenthavelowgradesAction(),new DisplayAllAbsenceOfMyStudentsAction(),
                new ExecuteAllmarksAction(),new ExhibitIfmyStudentsHaveLowAbsenceAction(),new GenerateReportsForStudentswhoFailAction(),
                new SendExcuseToTeacherAction(),new ShowAllgradesOfMyStudentAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
