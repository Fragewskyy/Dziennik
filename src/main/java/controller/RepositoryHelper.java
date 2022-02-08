

package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.peoplesRoles.Student;
import repository.StudentDAO;
import repository.UserDAO;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryHelper {


    public void generateCertificate(Integer studentid) throws SQLException {
        System.out.println("dizała");
        Rectangle pageSize = new Rectangle(800,1000);
        pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
        Document document = new Document(pageSize);

        String getNameAndSurnaemQuery="SELECT name, surname from users where users.user_id = (select user_id from " +
                "student where student_id = " + studentid + ")";
        try {

            Connection connection=SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getNameAndSurnaemQuery);
            resultSet.next();
            String namsurname=resultSet.getString("name")+resultSet.getString("surname");
            System.out.println(namsurname);
            PdfWriter.getInstance(document, new FileOutputStream(namsurname));


        } catch (Exception e) {
            e.printStackTrace();
        }
        Font font = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
        Connection connection= null;
        document.open();
        String showgradesQuery="SELECT (SELECT (SELECT subject_name FROM dziennik.subjects where dziennik.lessons" +
                ".subject_id=dziennik.subjects.subject_id) FROM dziennik.lessons where dziennik.grades" +
                ".lesson_id=dziennik.lessons.lesson_id) as subject, round(avg(grade)) as grade  FROM dziennik.grades " +
                "where student_id="+studentid+" group by subject;";
        try {
            connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(showgradesQuery);


            Image img = Image.getInstance("godło polskimniejszy.jpg");
            img.setAbsolutePosition(200,600);
            document.add(img);
            StringBuilder pdfcontent= new StringBuilder("\n\n\n\n\nSZKOŁA PODSTAWOWA W RADOMIU");

            System.out.println(pdfcontent);
            while(resultSet.next()) {
                String gradelinestring=resultSet.getString("subject")+": "+ resultSet.getInt("grade")+"\n";

                pdfcontent.append(gradelinestring);


            }
            System.out.println(pdfcontent);
            Paragraph paragraph = new Paragraph(pdfcontent.toString());
            document.add(paragraph);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        document.close();
    }
    public void showStudentList() {
        StudentDAO studentDAO = new StudentDAO();
        UserDAO userDAO = new UserDAO();
        int iter = 0;
        try {
            for (Student student : studentDAO.getAll()) {
                iter += 1;
                String query = "SELECT class_name from classes WHERE class_id = (select class_id from student where " +
                        "student_id = " + student.studentId + ");";
                Connection connection = SQLController.Connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                try {

                    resultSet.next();
                    System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Current class: " + resultSet.getString("class_name"));
                } catch (SQLException e) {
                    System.out.println(iter + ". " + userDAO.get(student.userId).name + " " + userDAO.get(student.userId).surname + " | Not assigned to any class");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
