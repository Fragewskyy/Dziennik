package controller.TeacherActions;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.parser.Path;
import com.sun.scenario.effect.ImageData;
import controller.Action;
import controller.SQLController;
import repository.ClassDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateCertificateForAllStudentsAction implements Action {
    public static ArrayList<Integer> showstudentsfromclass(String classid){
        java.util.List<Integer> result = new ArrayList<Integer>();
        String query = "SELECT student_id FROM dziennik.students where class_id="+classid+";";
        try {
            Connection connection= SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                result.add(resultSet.getInt("student_id"));
            }
            return (ArrayList<Integer>) result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public void executeQuery() {
        ClassDAO classDAO=new ClassDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println(" write your class name");
        String classname=scanner.next();
        String classid=classDAO.getclassbyname(classname);
        ArrayList<Integer> classStudents=showstudentsfromclass(classid);

        for(int studentid:classStudents) {

            Rectangle pageSize = new Rectangle(800,1000);
            pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
            Document document = new Document(pageSize);

            String getNameAndSurnaemQuery="SELECT (SELECT name FROM dziennik.users where dziennik.users.user_id=dziennik.students.user_id) as name,(SELECT surname FROM dziennik.users where dziennik.users.user_id=dziennik.students.user_id) as surname from dziennik.students where student_id="+String.valueOf(studentid)+";";
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
            String showgradesQuery="SELECT (SELECT (SELECT subject_name FROM dziennik.subjects where dziennik.lessons.subject_id=dziennik.subjects.subject_id) FROM dziennik.lessons where dziennik.grades.lesson=dziennik.lessons.lesson_id) as subject, round(avg(grade)) as grade  FROM dziennik.grades where student_id="+studentid+" group by subject;";
            try {
                connection = SQLController.Connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(showgradesQuery);


                Image img = Image.getInstance("godło polskimniejszy.jpg");
                document.add(img);
                StringBuilder pdfcontent= new StringBuilder("SZKOŁA PODSTAWOWA W RADOMIU\ner");

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


    }

    @Override
    public String getlabel() {

        return "generate cartificate for all student";
    }
}
