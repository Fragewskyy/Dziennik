package daos;

import roleInterfaces.StudentInterface;
import roleInterfaces.UserInterface;

import java.sql.*;

public class studentDAO implements StudentInterface {
    public void displayAllGrades() {
        String query = "SELECT grade, (SELECT subject_name FROM subjects WHERE grades.subject_id = subjects" +
                ".subject_id) FROM subjects WHERE student_id = 2";

    }

    public void displayAbsences() {

    }


}
