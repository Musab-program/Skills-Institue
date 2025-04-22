
package skills;

import java.util.Scanner;
import java.sql.*;

public class Classes {
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    //variable
    private int id;
    private String name;
    private int floor;
    //object for input
    Scanner input=new Scanner (System .in);

    //capsolation
    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        while(true){
            if(name.matches("[a-zA-Z0-9\\-\\s]+")){
               this.name = name;
                break;
            }
            else{
                System.out.println("syntax error");
                System.out.println("Enter again: ");
                name=input.nextLine();
                setName(name);
            
            }
                
        }
        
    }

    
    public int getFloor() {
        return floor;
    }

    
    public void setFloor(int floor) {
         while(true){
            if(floor>0 && floor<5){
                this.floor = floor;
                break;
            }
            else{
                System.out.println("syntax error");
                System.out.println("Enter again: ");
                floor =input.nextInt();
                setFloor(floor);
            
            }
                
        }
        
    }
    
    
    //to add Classes
    public void add(Classes cal) {
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            String sql = "INSERT INTO CLASS ( CLA_NAME ,CLA_FLOOR) VALUES ( ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, cal.name);
            statement.setInt(2, cal.floor);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new class added.");
            }

        }
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }


    
    //to veiw Classes
    public void veiw() {
        
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rs=stm.executeQuery("select * from class");
        
        while(rs.next()) {
                 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
        
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }

    
    
    //to edit Classes
    public void edit(String name) {
        
    
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
              // SQL query to find the classses by name
            String searchSql = "SELECT * FROM CLASS WHERE CLA_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any classses were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No classses found with names similar to: " + name);
                return;
            }

            // Display the results and ask the user to confirm the student
            System.out.println("classses found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int classId = resultSet.getInt("CLA_ID");
                String className = resultSet.getString("CLA_NAME");
                System.out.println(index + ". " + className + " (ID: " + classId + ")");
                ids[index - 1] = classId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the class you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Floor");
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
                    updateSql = "UPDATE CLASS SET CLA_NAME = ? WHERE CLA_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the new floor:");
                    int newfloor = scanner.nextInt();
                    setFloor(newfloor);
                    updateSql = "UPDATE CLASS SET FLOOR = ? WHERE CLA_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newfloor);
                    updateStatement.setInt(2, selectedId);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            // Execute the update statement
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("CLASS with ID " + selectedId + " has been updated.");
            } else {
                System.out.println("Update failed.");
            }
            

            } 
        catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to delete Classes
    public void delete(String name) {
        
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // SQL query to find all classes with names similar to the input
            String search_delt = "SELECT CLA_ID, CLA_NAME FROM CLASS WHERE CLA_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any classes were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No classes found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("Classes found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int classId = resultSet.getInt("CLA_ID");
                String className = resultSet.getString("CLA_NAME");
                System.out.println(index + ". " + className + " (ID: " + classId + " )");
                ids[index - 1] = classId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to delete
            System.out.println("Enter the number of the class you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected student based on the ID
            String deleteSql = "DELETE FROM CLASS WHERE CLA_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("class with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
   
    
    
    //to search Classes
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM CLASS WHERE CLA_NAME LIKE ?";
            PreparedStatement statement = connection.prepareStatement(search_stu);

            // Set the keyword to search for in title or genre
            statement.setString(1, "%" + name + "%");

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            while(resultSet.next()) {
                 System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getString(3));
            }
        } 
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
