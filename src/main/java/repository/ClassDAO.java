package repository;

import controller.SQLController;

import java.sql.*;
import java.util.ArrayList;

public class ClassDAO implements Dao{
    public String getclassbyname(String name){
        String query = "SELECT class_id FROM dziennik.classes where class_name='"+name+"';";
        ResultSet resultSet=null;
        try {
            Connection connection=SQLController.Connect();
            Statement statement=connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            resultSet.getInt("class_id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Object get(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public void update(int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dziennik.classes WHERE admin_id = " + id + ";";
        Connection connection = DriverManager.getConnection(SQLController.URL, SQLController.USERNAME,
                SQLController.PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
    }
}
