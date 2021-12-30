package controller.PrincipalActions;

import controller.Action;
import model.User;
import repository.UserDAO;

import java.sql.SQLException;

public class DisplayyAllPersonsAction implements Action {
    @Override
    public void executeQuery() throws SQLException {
        UserDAO userDAO = new UserDAO();
        for (User user : userDAO.getAll()) {
            System.out.println(user.userId +
                    ". Name: " + user.name + " " + user.surname + " | Role: " + userDAO.getRoleByLogin(user.login));
        }

    }

    @Override
    public String  getlabel() {
        return "Displayy All Persons";
    }
}
