package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenerateCertificate {


    public void executeQuery(Integer studentid) throws SQLException {
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


    public String getlabel() {
        return null;
    }
}
