package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.StudentDAO;
import repository.TeacherDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class ModifyGradeAction implements Action {
    public static Map<Integer,String> creategrademap(){
        TeacherDAO teacherDAO=new TeacherDAO();
        StudentDAO studentDAO=new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("student login pls");
        String studentid=studentDAO.getstudentid(scanner.nextLine());
        String teachersubject=String.valueOf(teacherDAO.getsubject());
        String show="SELECT grade ,date FROM dziennik.grades inner join dziennik.lessons on dziennik.grades.lesson=dziennik.lessons.lesson_id where student_id="+studentid+" and (SELECT subject_name FROM dziennik.subjects where dziennik.lessons.subject_id=dziennik.subjects.subject_id)  ='"+teachersubject+"'  ;";
        Connection connection = null;
        try {
            connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(show);
            int index = 1;
            Map<Integer, String> grademaplist = new HashMap<Integer, String>();
            while (resultSet.next()) {
                grademaplist.put(index, resultSet.getString("grade") + ":" + resultSet.getString("date"));
                index++;
            }
            return grademaplist;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void executeQuery() {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> finalgradesMapList=ModifyGradeAction.creategrademap();
        System.out.println("select your grade");

        for(int listindex=0; listindex<finalgradesMapList.size(); listindex++) {
            finalgradesMapList.get(listindex);
        }
        //todo
        //ten no zrób metode zmodufikuj ocene tutaj tylko, i wogule dokończ to co teraz widzisz

    }

    @Override
    public String getlabel() {
        return "modify grade ";
    }
}
