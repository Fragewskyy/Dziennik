package controller.TeacherActions;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import controller.Action;
import controller.SQLController;
import repository.ClassDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateCertificateForAllStudentsAction implements Action {
    public static ArrayList<Integer> showstudentsfromclass(String classid){
        java.util.List<Integer> result = new ArrayList<Integer>();
        String query = "SELECT student_id FROM dziennik.students where class="+classid+";";
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
        String classname=scanner.next();
        String classid=classDAO.getclassbyname(classname);
        ArrayList<Integer> classusers=showstudentsfromclass(classid);
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    @Override
    public String getlabel() {

        return "generate cartificate for all student";
    }
}
