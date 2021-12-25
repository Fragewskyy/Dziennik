package controller.TeacherActions;

import controller.Action;
import model.User;
import repository.ClassDAO;
import repository.TeacherDAO;
import repository.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class AddStudentToClassAction implements Action {
    @Override
    public void executeQuery() {


    }

    @Override
    public String getlabel() {

        return " add student to class";
    }
}
