package controller;

import java.sql.SQLException;

public interface Action {
    void executeQuery() throws SQLException;
    String getlabel();
}
