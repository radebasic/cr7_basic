import com.mysql.cj.xdevapi.Result;

import java.sql.*;
import java.util.*;

public class Main {

    public void ShowAllStudents()  {

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url =  "jdbc:mysql://localhost:3306/cr7_basic";
        String user     = "root";
        String password = "";
        Connection connection = null;

            connection = DriverManager.getConnection(url, user, password);

         PreparedStatement ps = connection.prepareStatement("SELECT student_name,student_surname, student_email,classes.class_name FROM students inner join classes on students.ID_class=classes.ID_class");
         ResultSet rs=ps.executeQuery();

         while(rs.next()){
             System.out.println(rs.getString("student_name") + " "+rs.getString("student_surname"));
             System.out.println(rs.getString("student_email"));
             System.out.println(rs.getString("class_name"));
             System.out.println("______________________");
         }
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException ee){


        }

    }

    public void showAllTeachers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url =  "jdbc:mysql://localhost:3306/cr7_basic";
            String user     = "root";
            String password = "";
            Connection connection = null;

            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT * from teachers");
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("teacher_name") + " "+rs.getString("teacher_surname"));
                System.out.println(rs.getString("teacher_email"));
                System.out.println("______________________");
            }
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException ee){

        }
    }

    public void showAllClasses(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url =  "jdbc:mysql://localhost:3306/cr7_basic";
            String user     = "root";
            String password = "";
            Connection connection = null;

            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT * from classes");
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("class_name"));
                System.out.println("______________________");
            }
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException ee){

        }
    }


    public void showAllClassesOfTeacher(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url =  "jdbc:mysql://localhost:3306/cr7_basic";
            String user     = "root";
            String password = "";
            Connection connection = null;

            connection = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = connection.prepareStatement("SELECT teachers.ID_teacher,teachers.teacher_name, teachers.teacher_surname, teachers.teacher_email, classes.class_name FROM teachers\n" +
                    "inner join teachers_classes on teachers.ID_teacher=teachers_classes.ID_teacher\n" +
                    "inner join classes on classes.ID_class=teachers_classes.ID_class\n" +
                    "where teachers.ID_teacher=?");

            System.out.println("Enter teacher ID");
            Scanner scc=new Scanner(System.in);
            int id=scc.nextInt();
            ps.setInt(1, id);

            ResultSet rs=ps.executeQuery();

            int FirstTime=0;
            while(rs.next()){
               if(FirstTime==0){
                   System.out.println("ID "+ rs.getInt("ID_teacher")+": Teacher "+rs.getString("teacher_name") + " "+rs.getString("teacher_surname")+" teaches");
                   System.out.println(rs.getString("class_name"));
               } //first row
                else {
                   System.out.println(rs.getString("class_name"));
               }
                FirstTime=1;
            }
            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        catch (ClassNotFoundException ee){

        }
    }


    public static void main(String[] args) {

        Main mm=new Main();
        int choose=0;

        while(choose!=5){
            System.out.println("Choose Menu options");
            System.out.println("1. Display all students");
            System.out.println("2. Display all teachers");
            System.out.println("3. Display all classes");
            System.out.println("4. Display all classes of teacher");
            System.out.println("5.Exit");

            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();

            switch (choose){

              case 1: mm.ShowAllStudents();    break;
                case 2: mm.showAllTeachers();  break;
                case 3: mm.showAllClasses(); break;
                case 4:mm.showAllClassesOfTeacher(); break;
                case 5:  break;

                default:
                    throw new IllegalStateException("Unexpected value: " + choose);
            }


        }
    }

}
