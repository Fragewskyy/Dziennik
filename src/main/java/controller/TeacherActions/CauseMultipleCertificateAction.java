

        package controller.TeacherActions;

        import controller.Action;
        import controller.GenerateCertificate;
        import controller.SQLController;
        import model.peoplesRoles.Student;
        import repository.StudentDAO;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Scanner;

public class CauseMultipleCertificateAction implements Action {
    @Override
    public void executeQuery() {
        StudentDAO studentDAO = new StudentDAO();
        System.out.println("SELECT students(write numbers with commas ");
        ArrayList<Student> studentschoicelist = null;
        try {
            studentschoicelist = studentDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int index=1;

        for (Student student : studentschoicelist) {
            GenerateCertificate generateCertificate = new GenerateCertificate();
            String getuserNameAndSurnameQuery="SELECT name,surname FROM dziennik.users where user_id="+student.userId+";";
            try {
                Connection connection= SQLController.Connect();
                Statement statement= connection.createStatement();
                ResultSet resultSet = statement.executeQuery(getuserNameAndSurnameQuery);
                while (resultSet.next()) {
                    System.out.println(index+":"+resultSet.getString("name")+" "+resultSet.getString("surname"));
                    index++;
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Scanner scanner = new Scanner(System.in);
            List<String> studentindexes = Arrays.asList(scanner.next().split(","));
            for(String studentid:studentindexes) {
                int indexx = Integer.parseInt(studentid) - 1;
                Student selectedStudent = studentschoicelist.get(indexx);


                try {
                    generateCertificate.executeQuery(selectedStudent.getStudentId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getlabel() {
        return "Create multiple certificates";
    }
}
