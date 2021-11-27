package daos;

import roleInterfaces.StudentInterface;
import roleInterfaces.UserInterface;

import java.sql.*;

public class studentDAO implements StudentInterface {
    public void displayAllGrades() {
        String query = "SELECT (SELECT subject_name FROM subjects WHERE grades.subject_id = subjects.subject_id) AS Subject_name, grade FROM grades WHERE student_id = 2";

    }

    public void displayAbsences() {

    }


}
