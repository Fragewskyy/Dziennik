package repository;

import controller.SQLController;
import model.peoplesRoles.Admin;
import model.peoplesRoles.Teacher;
import view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherDAO implements Dao{
    public String getteacherid( String login ){
        String query="SELECT teacher_id  FROM dziennik.teacher where user_id=(SELECT user_id FROM dziennik.users where login='"+login+");";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,SQLController.PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return String.valueOf(resultSet.getInt("teacher_id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ///tu będzie error spokojnie
        return "error";
    }
    public String  getsubject(){
        UserDAO userdao=new UserDAO();
        String userid=null;
        try {
             userid=String.valueOf(userdao.getId(MainView.getLogin()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query="SELECT (SELECT subject_name FROM dziennik.subjects where dziennik.subjects.subject_id=dziennik.teacher.subject_id) AS sb FROM dziennik.teacher where user_id="+userid+"; ";
        try {
            Connection connection=SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("sb");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int getteacheridbyname(String login ){
        String query = "SELECT teacher_id from dziennik.teacher ,dziennik.users where login='"+login+"' and dziennik.users.user_id=dziennik.teacher.user_id ;";

        Statement statement = null;
        try {
            Connection connection = SQLController.Connect();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("teacher_id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;

    }
    @Override
    public Teacher get(int id) throws SQLException {
        String query = "SELECT * FROM dziennik.teacher WHERE teacher_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        return new Teacher(resultSet.getInt("teacher_id"), resultSet.getInt("user_id"));
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        ArrayList<Teacher> result = new ArrayList<>();
        String query = "SELECT * FROM dziennik.teacher;";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            result.add(new Teacher(resultSet.getInt("teacher_id"), resultSet.getInt("user_id")));
        }

        return result;
    }

    @Override
    public void update(int id) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nowe user_id: ");
        int u = scanner.nextInt();
        String query = "UPDATE dziennik.teacher SET user_id = " + u + " WHERE teacher_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.admin WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }

    public void save(Teacher teacher) throws SQLException {
        String query = "INSERT INTO dziennik.teacher (user_id) VALUES (" + teacher.userId + ");";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
}
