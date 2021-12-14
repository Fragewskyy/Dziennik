package config;

import controller.Action;
import controller.TeacherActions.*;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;
import view.ConsoleView;
import view.Menu;

import java.util.Arrays;
import java.util.List;

public class TeacherConfig implements Config{
    View.View view = new ConsoleView();
    @Override
    public List<Action> inicializactions() {
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction(), new AddStudentToClassAction(),new CauseMultipleCertificateAction(),new CreateNewGuardianAndAssignStudnetAction(),new GenerateCertificateForAllStudentsAction(),new GenerateStudentCertificateAction(),new InsertStudentToGuardian(),new MakeNewStudentAction(),new ModifyGradeAction(),new VerifyStudentsAbsenceAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return new Menu(view);
    }
}
