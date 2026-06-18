
package javaapplication28;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class JavaApplication28
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/School",
                "postgres",
                "saravana1618");

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println(" STUDENT RECORD MENU ");
            System.out.println("1. Insert");
            System.out.println("2. Display");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    
                    System.out.print("Enter Student Name: ");
                    String name = sc.next();
                    System.out.print("Enter Student Age: ");
                    int age = sc.nextInt();
                    PreparedStatement Insert = c.prepareStatement("INSERT INTO Student(Name,Age) VALUES(?,?)");
                    Insert.setString(1, name);
                    Insert.setInt(2, age);

                    int insertResult = Insert.executeUpdate();
                    if (insertResult >0)
                    {
                        System.out.println("Record Inserted Successfully");
                    } 
                    else 
                    {
                        System.out.println("Insertion Failed");
                    }
                    break;

                case 2:

                    Statement st = c.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM Student");
                    System.out.println("NAME     AGE");
                    while(rs.next()) 
                    {
                        String studentName = rs.getString("Name");
                        int studentAge = rs.getInt("Age");
                        System.out.println(studentName + "     " + studentAge);
                    }
                    break;

                case 3:

                    System.out.print("Enter Student Name To Update: ");
                    String updateName = sc.next();
                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();
                    PreparedStatement Update =c.prepareStatement( "UPDATE Student SET Age=? WHERE Name=?");
                                        
                    Update.setInt(1, newAge);
                    Update.setString(2, updateName);

                    int updateResult = Update.executeUpdate();
                    if
                            (updateResult >0) 
                    {
                        System.out.println("Record Updated Successfully");
                    } 
                    else 
                    {
                        System.out.println("Student Not Found");
                    }
                    break;

                case 4:

                    System.out.print("Enter Student Name To Delete: ");
                    String deleteName = sc.next();
                    PreparedStatement Delete =c.prepareStatement("DELETE FROM Student WHERE Name=?");              
                    Delete.setString(1, deleteName);
                    int deleteResult = Delete.executeUpdate();
                    if (deleteResult > 0)
                    {
                        System.out.println("Record Deleted Successfully");
                    } 
                    else
                    {
                        System.out.println("Student Not Found");
                    }
                    break;
                case 5:
                    System.out.println("Program Exited Successfully");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (choice != 5);   
    }
}