package config;

import controller.Action;
import controller.PrincipalActions.CreateNewClassAndAssignToTeacherAction;
import controller.TeacherActions.*;
import controller.UserActions.ChangePasswordAction;
import controller.UserActions.LogOutAction;
import controller.UserActions.SendTextMessegeAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherConfig implements Config{
    @Override
    public List<Action> inicializactions() {
        Action helper[]=new Action[] {new ChangePasswordAction(), new LogOutAction(),new SendTextMessegeAction(), new AddStudentToClassAction(),new CauseMultipleCertificateAction(),new CreateNewGuardianAndAssignStudnetAction(),new GenerateCertificateForAllStudentsAction(),new GenerateStudentCertificateAction(),new InsertStudentToGuardian(),new MakeNewStudentAction(),new ModifyGradeAction(),new VerifyStudentsAbsenceAction()};

        List<Action> actions= Arrays.asList(helper);
        return actions;
    }

    @Override
    public Menu inicializemenu() {
        return null;
    }
}
