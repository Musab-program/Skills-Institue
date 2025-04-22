
package skills;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;



public class Stu_family extends Person{
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    
    //variable
    private String relation;
    private int stu_id;
    
    
    //casolation

    public String getRelation() {
        return relation;
    }

    
    public void setRelation(String relation) {
        while(true){
            if (relation.matches("[a-zA-Z\\s]+")){
                this.relation = relation;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                relation=input.nextLine();
                setRelation(relation);
            }
        }
    }

    
    public int getStu_id() {
        return stu_id;
    }

    
    public void setStu_id() {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedTeacherId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all students from the database
            String sql = "SELECT S_ID, S_NAME FROM STUDENT";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            // Display all students to the user
            System.out.println("Available students: ");
            int index = 1;
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("S_NAME"));
                index++;
            }

            
            // If no results found
            if (index == 1) {
                System.out.println("No students found.");
                return;
            }

            
            // Ask the user to select a students
            System.out.print("Select the student number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected students
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedTeacherId = rs.getInt("S_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected teacher's ID in a variable
            System.out.println("Selected student name: " + rs.getString("S_NAME"));
            stu_id=selectedTeacherId;
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to add student
    public void add(Stu_family member) {
        
            try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            String sql = "INSERT INTO STUDENT_FAMLIY ( F_NAME, F_PHONE, F_RELATION, GENDER, ADDRESS ,STUDENT_ID) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, member.name);
            statement.setInt(2, member.phone_num);
            statement.setString(3, member.relation);
            statement.setString(4, member.gender);
            statement.setString(5, member.address);
            statement.setInt(6, member.stu_id);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new member added.");
            }

        }
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            
        
    }
    
    
    
    //to edit student
    public void edit(String name) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
              // SQL query to find the student by name
            String searchSql = "SELECT * FROM STUDENT_FAMLIY WHERE F_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any members were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No members found with names similar to: " + name);
                return;
            }

            // Display the results and ask the user to confirm the members
            System.out.println("members found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int familyId = resultSet.getInt("F_ID");
                String familyName = resultSet.getString("F_NAME");
                System.out.println(index + ". " + familyName + " (ID: " + familyId + ")");
                ids[index - 1] = familyId;  // Store the ID in the array
                index++;
            }

            // Ask the user which members to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the member you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected members's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Relation");
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
                    updateSql = "UPDATE STUDENT_FAMLIY SET F_NAME = ? WHERE F_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the new phone number:");
                    int newPhone = scanner.nextInt();
                    setPhone_num(newPhone);
                    updateSql = "UPDATE STUDENT_FAMLIY SET F_PHONE = ? WHERE F_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newPhone);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 3:
                    System.out.println("Enter the new  Relation :");
                    int newRelation = scanner.nextInt();
                    updateSql = "UPDATE STUDENT_FAMLIY SET RELATION = ?  WHERE F_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newRelation);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 4:
                    System.out.println("Enter the new gender (male/female):");
                    String newGender = scanner.next();
                    setGender(newGender);
                    updateSql = "UPDATE STUDENT_FAMLIY SET GENDER = ? WHERE F_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newGender);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 5:
                    System.out.println("Enter the new address:");
                    scanner.nextLine();  // Consume newline
                    String newAddress = scanner.nextLine();
                    setAddress(newAddress);
                    updateSql = "UPDATE STUDENT_FAMLIY SET ADDRESS = ? WHERE F_ID = ?";
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
                System.out.println("Member with ID " + selectedId + " has been updated.");
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
            
            // SQL query to find all member with names similar to the input
            String search_delt = "SELECT F_ID, F_NAME FROM STUDENT_FAMLIY WHERE F_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any member were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No member found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("member found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int studentId = resultSet.getInt("F_ID");
                String studentName = resultSet.getString("F_NAME");
                System.out.println(index + ". " + studentName + " (ID: " + studentId + " )");
                ids[index - 1] = studentId;  // Store the ID in the array
                index++;
            }

            // Ask the user which member to delete
            System.out.println("Enter the number of the member you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected member's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected member based on the ID
            String deleteSql = "DELETE FROM STUDENT_FAMLIY WHERE F_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("Member with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
     //to search student
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM STUDENT_FAMLIY WHERE F_NAME LIKE ?";
            PreparedStatement statement = connection.prepareStatement(search_stu);

            // Set the keyword to search for in title or genre
            statement.setString(1, "%" + name + "%");

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            while(resultSet.next()) {
                 System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getString(3)+"  "+resultSet.getFloat(4)+"  "+resultSet.getString(5)+"  "+resultSet.getString(6)+"  "+resultSet.getInt(7));
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
        ResultSet rs=stm.executeQuery("SELECT * FROM STUDENT_FAMLIY");
        while(rs.next())
        {
                 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getInt(7));
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    

    }
}


