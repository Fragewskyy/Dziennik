package Errors;

import model.peoplesRoles.Guardian;
import model.peoplesRoles.Student;
import repository.GuardianDAO;
import repository.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class SameGuardianExistError {
    public static boolean checksamestudent(String login, String password,String name,String surname){
        GuardianDAO guardianDAO=new GuardianDAO();

        try {
            List<Guardian> guardians= guardianDAO.getAll();
            for (Guardian guardian: guardians){
                List<String> info=SamePersonChecker.getInformations(guardian.getUserId());
                String xlogin=info.get(0);
                String xpassword=info.get(1);
                String xname=info.get(2);
                String xsurname=info.get(3);
                if(guardian.equals(xlogin)  | guardian.equals(xpassword)| guardian.equals(xname) | guardian.equals(xsurname)){
                    return false;


                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
