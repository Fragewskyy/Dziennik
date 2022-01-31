package controller.GuardianActions;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import controller.Action;
import controller.SQLController;
import model.User;
import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;
import repository.UserDAO;
import s.MainView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

public class GenerateReportsForStudentswhoFailAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        GuardianDAO guardianDAO = new GuardianDAO();
        StudentDAO studentDAO = new StudentDAO();
        Guardian guardian = guardianDAO.get(userDAO.getId(MainView.getLogin()));
        String query = "select avg(grade) as avgGrades, student_id,  (select subject_name from subjects where " +
                "subjects.subject_id = (select subject_id from lessons where lesson_id = grades.lesson_id)) as " +
                "subjectname from grades WHERE student_id in (select student_id from student where guardian_Id = " + guardian.guardianId +
        " " +
                ") GROUP BY student_id, lesson_id ORDER BY student_id DESC;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String content = "";
        while (resultSet.next()){
            if(resultSet.getDouble("avgGrades") < 1.6){
                Student student = studentDAO.get(resultSet.getInt("student_id"));
                User user = userDAO.get(student.userId);
                System.out.println("!ATTENTION! Student " + user.name + " " + user.surname + " is going to fail " +
                        "promotion from " + resultSet.getString(
                        "subjectname") + ". Average equals " + resultSet.getDouble("avgGrades"));
                content += "!ATTENTION! Student " + user.name + " " + user.surname + " is going to fail " +
                        "promotion from " + resultSet.getString(
                        "subjectname") + ". Average equals " + resultSet.getDouble("avgGrades") + "\n";
            }
        }
        generatePDF(content);

    }

    public void generatePDF(String content) {
        String FILE = "C:/Users/Franek/Desktop/raport.pdf";
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
        Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
        Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            Paragraph preface = new Paragraph();
            for (int i = 0; i < 1; i++) {
                preface.add(new Paragraph(" "));
            }
            preface.add(new Paragraph("Your student have to low grades, list of them below", catFont));
            preface.add(new Paragraph(content, smallFont));
            document.add(preface);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getlabel() {
        return "Generate reports for students who are going to fail promotion.";
    }
}
