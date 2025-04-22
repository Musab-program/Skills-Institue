
package skills;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Student extends Person {
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    
    //vairable
    private int birth ;
    private int age =LocalDate.now().getYear()- birth;


    //capsolition
    public int getBirth() {
        return birth;
    }

    
    public void setBirth(int birth) {
        this.birth =birth;
    }
  
    
    public int getAge() {
        return age;
    }


    
    
    
    
    //operation
    
     //to add student
    public void add(Student stu1) {
        
            try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            String sql = "INSERT INTO STUDENT ( S_NAME, S_PHONE, BIRTH, GENDER, ADDRESS ,AGE) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, stu1.name);
            statement.setInt(2, stu1.phone_num);
            statement.setInt(3, stu1.birth);
            statement.setString(4, stu1.gender);
            statement.setString(5, stu1.address);
            statement.setInt(6, stu1.age);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student added.");
            }

        }
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            
        
    }


     //to veiw student
    public void veiw() {
        
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rs=stm.executeQuery("select * from student");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    

    }

    
     //to edit student
    public void edit(String name) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
              // SQL query to find the student by name
            String searchSql = "SELECT * FROM STUDENT WHERE S_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any students were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No students found with names similar to: " + name);
                return;
            }

            // Display the results and ask the user to confirm the student
            System.out.println("Students found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int studentId = resultSet.getInt("S_ID");
                String studentName = resultSet.getString("S_NAME");
                System.out.println(index + ". " + studentName + " (ID: " + studentId + ")");
                ids[index - 1] = studentId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the student you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Birth Year");
            System.out.println("4. Gender");
            System.out.println("5. Address");
            int updateChoice = scanner.nextInt();

            //Give an itinail value
            String updateSql = null;
            PreparedStatement updateStatement = null;

            switch (updateChoice) {
                case 1:
                    System.out.println("Enter the new name: ");
                    scanner.nextLine();  // Consume newline
                    String newName = scanner.nextLine();
                    setName(newName);
                    updateSql = "UPDATE STUDENT SET S_NAME = ? WHERE S_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the new phone number:");
                    int newPhone = scanner.nextInt();
                    setPhone_num(newPhone);
                    updateSql = "UPDATE STUDENT SET S_PHONE = ? WHERE S_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newPhone);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 3:
                    System.out.println("Enter the new birth year:");
                    int newBirthYear = scanner.nextInt();
                    int newAge=LocalDate.now().getYear()-newBirthYear;
                    updateSql = "UPDATE STUDENT SET BIRTH = ? , AGE =? WHERE S_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newBirthYear);
                    updateStatement.setInt(2, newAge);
                    updateStatement.setInt(3, selectedId);
                    break;
                case 4:
                    System.out.println("Enter the new gender (male/female):");
                    String newGender = scanner.next();
                    setGender(newGender);
                    updateSql = "UPDATE STUDENT SET GENDER = ? WHERE S_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newGender);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 5:
                    System.out.println("Enter the new address:");
                    scanner.nextLine();  // Consume newline
                    String newAddress = scanner.nextLine();
                    setAddress(newAddress);
                    updateSql = "UPDATE STUDENT SET ADDRESS = ? WHERE S_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newAddress);
                    updateStatement.setInt(2, selectedId);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            // Execute the update statement
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + selectedId + " has been updated.");
            } else {
                System.out.println("Update failed.");
            }
            

            } 
        catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
    
    
    
     //to delete student
    public void delete(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // SQL query to find all students with names similar to the input
            String search_delt = "SELECT S_ID, S_NAME FROM STUDENT WHERE S_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any students were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No students found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("Students found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int studentId = resultSet.getInt("S_ID");
                String studentName = resultSet.getString("S_NAME");
                System.out.println(index + ". " + studentName + " (ID: " + studentId + " )");
                ids[index - 1] = studentId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to delete
            System.out.println("Enter the number of the student you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected student based on the ID
            String deleteSql = "DELETE FROM STUDENT WHERE S_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("Student with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
     //to search student
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM STUDENT WHERE S_NAME LIKE ?";
            PreparedStatement statement = connection.prepareStatement(search_stu);

            // Set the keyword to search for in title or genre
            statement.setString(1, "%" + name + "%");

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            while(resultSet.next()) {
                 System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getInt(3)+"  "+resultSet.getInt(4)+"  "+resultSet.getString(5)+"  "+resultSet.getString(6)+"  "+resultSet.getInt(7));
            }
        } 
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
}
