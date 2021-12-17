package config;

import repository.UserDAO;

public class Configchanger {

    public static  Config inicializeConfig(String login){
        UserDAO userDAO=new UserDAO();
        switch(userDAO.getRoleByLogin(login)){
            case "teacher":
                return new TeacherConfig();

            case "admin":
                return new AdminConfig();

            case "dyrektor":
                return new PrincipalConfig();
            case "guardian":
                return new GuardianConfig();
            case "student":
                return new StudentConfig();
        }
        /**
        System.out.println("narazie nie działa niestety :(");
        System.exit(0);
         **/
        return new TeacherConfig();
    }
}
