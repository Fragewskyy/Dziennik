package controller.TeacherActions;

import controller.Action;
import controller.SQLController;
import model.peoplesRoles.Teacher;
import repository.StudentDAO;
import repository.TeacherDAO;
import view.MainView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ModifyGradeAction implements Action {
    public static Map<Integer,List<String>> creategrademap(){
        TeacherDAO teacherDAO=new TeacherDAO();
        StudentDAO studentDAO=new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("student login pls");
        String studentid=studentDAO.getstudentid(scanner.nextLine());
        String teacherid=teacherDAO.getteacherid(MainView.getLogin());
        String show="SELECT grade_id,(SELECT subject_name FROM dziennik.subjects where dziennik.lessons.subject_id=dziennik.subjects.subject_id) ,grade ,date FROM dziennik.grades inner join dziennik.lessons on dziennik.grades.lesson=dziennik.lessons.lesson_id where \n" +
                "                student_id="+studentid+" and grades.lesson in (SELECT lesson_id FROM dziennik.lessons where teacher_id="+teacherid+")  ;";
        Connection connection = null;
        try {
            connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(show);
            int index = 1;
            Map<Integer, List<String>> grademaplist = new HashMap<Integer, List<String>>();
            while (resultSet.next()) {
                List<String> helper=Arrays.asList(new String[]{resultSet.getString("grade"), resultSet.getString("date"),String.valueOf(resultSet.getInt("grade_id"))});
                grademaplist.put(index, helper);
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
        Map<Integer, List<String>> finalgradesMapList=ModifyGradeAction.creategrademap();
        System.out.println("select your grade");

        for(int listindex=1; listindex<finalgradesMapList.size(); listindex++) {
            System.out.println(listindex+"="+finalgradesMapList.get(listindex).get(0)+":"+finalgradesMapList.get(listindex).get(1));
        }
        int inputnumber=scanner.nextInt();
        System.out.println("what new grade");
        String newgrade=scanner.next();

        List<String> finalgrade=Arrays.asList(new String[]{finalgradesMapList.get(inputnumber).get(0), newgrade});
        finalgradesMapList.replace(inputnumber,finalgrade);
        String query="update dziennik.grades set grade=3 where grade_id="+finalgradesMapList.get(inputnumber).get(2)+";";
        Connection connection = null;
        try {
            connection = SQLController.Connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("you updated a grade ,nice bro");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public String getlabel() {
        return "modify grade ";
    }
}

//ten no zrób metode zmodufikuj ocene tutaj tylko, i wogule dokończ to co teraz widzisz