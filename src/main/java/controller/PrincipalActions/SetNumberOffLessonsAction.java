package controller.PrincipalActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.ClassDAO;
import repository.TeacherDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SetNumberOffLessonsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        TeacherDAO teacherDAO = new TeacherDAO();
        Scanner scanner = new Scanner(System.in);

        String query = "SELECT * FROM CLASSES";
        SQLController.Connect();
        Statement statement = SQLController.Connect().createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> listOfClassIds = new ArrayList<>();
        int iter = 0;
        while (resultSet.next()){
            iter += 1;
            listOfClassIds.add(resultSet.getInt("class_id"));
            System.out.println(resultSet.getInt("class_id") + ". " + resultSet.getString("class_name"));
        }

        int chosenClass;
        while (true) {
            try {
                System.out.print("Select number of the class(must be between 1 and " + iter +
                        "): ");
                chosenClass = scanner.nextInt();
                if (chosenClass >= 1 && chosenClass <= iter) {
                    break;
                }
                System.out.println("Number must be an integer between 1 and " + iter + ".");

            } catch (InputMismatchException e) {
                System.out.println("Number must be an integer between 1 and " + iter + ".");
                scanner.next();
            }
        }
        resultSet.close();

        /*-------------------------------------*/

        ArrayList<Integer> listOfSubjectIds = new ArrayList<>();
        String query1 = "SELECT * FROM dziennik.subjects;";

        ResultSet resultSet1 = statement.executeQuery(query1);

        int iter1 = 0;
        while (resultSet1.next()) {
            iter1+=1;
            listOfSubjectIds.add(resultSet1.getInt("subject_id"));
            System.out.println(resultSet1.getInt("subject_id") + ". " + resultSet1.getString("subject_name"));
        }
        int chosenSubject;
        while (true) {
            try {
                System.out.print("Select number of the subject(must be between 1 and " + iter1 +
                        "): ");
                chosenSubject = scanner.nextInt();
                if (chosenSubject >= 1 && chosenSubject <= iter1) {
                    break;
                }
                System.out.println("Number must be an integer between 1 and " + iter1 + ".");

            } catch (InputMismatchException e) {
                System.out.println("Number must be an integer between 1 and " + iter1 + ".");
                scanner.next();
            }
        }

        int numberOfLessons;

        while (true) {
            try {
                System.out.print("Type number of lessons per year: ");
                numberOfLessons = scanner.nextInt();
                if (numberOfLessons >= 0) {
                    break;
                }
                System.out.println("Amount must be bigger or equals 0.");
            } catch (InputMismatchException e) {
                System.out.println("Amount must be an integer.");
                scanner.next();
            }
        }

        resultSet1.close();

        String query2 = "INSERT INTO lessonperyear(class_id, subject_id, amountOfLessons) VALUES(" + chosenClass + "," +
                " " + chosenSubject + ", " + numberOfLessons + ")";

        statement.executeUpdate(query2);

        System.out.println("Number of lessons per year successfully set.");



    }

    @Override
    public String getlabel() {
        return "Set number of lessons per year.";
    }
}
