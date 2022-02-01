package controller.PrincipalActions;

import controller.Action;
import model.User;
import repository.UserDAO;

import java.sql.SQLException;

public class DisplayyAllPersonsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        int iter = 0;
        for (User user : userDAO.getAll()) {
            iter+=1;
            System.out.println(iter +
                    ". Name: " + user.name + " " + user.surname + " | Role: " + userDAO.getRoleByLogin(user.login));
        }

    }

    @Override
    public String  getlabel() {
        return "Display all persons in system.";
    }
}
