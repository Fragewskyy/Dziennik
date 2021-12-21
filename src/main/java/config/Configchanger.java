package config;

import repository.UserDAO;

import java.sql.SQLException;

public class Configchanger {

    public static  Config inicializeConfig(String login) throws SQLException {
        UserDAO userDAO=new UserDAO();
        switch(userDAO.getRoleByLogin(login)){
            case "Teacher":
                return new TeacherConfig();
            case "Admin":
                return new AdminConfig();
            case "Principal":
                return new PrincipalConfig();
            case "Guardian":
                return new GuardianConfig();
            case "Student":
                return new StudentConfig();
        }
        /**
        System.out.println("narazie nie działa niestety :(");
        System.exit(0);
         **/
        return new TeacherConfig();
    }
}
