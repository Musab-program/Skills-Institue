
package skills;

import java.sql.*;
import java.util.Scanner;


public class Teacher extends Employee{
    
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    
    //varaible
    private String shift;
    private int emp_id;

    
    
    //capsolation
    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
         while(true){
             
            if ("PM".equals(shift) || "AM".equals(shift)){
                this.shift = shift;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                shift=input.nextLine();
                setShift(shift);
            }
        }   
    }
   
    
    // to set the employee id
    public void setEmp_id() {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedEmpId = -1;
       try (Connection connection = DriverManager.getConnection(url, username, password)){
           
           
         // Retrieve all employees from the database
            String sql = "SELECT E_ID, E_NAME FROM EMPLOYEES";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            // Display all teachers to the user
            System.out.println("Available employees: ");
            int index = 1;
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("E_NAME"));
                index++;
            }

            // If no results found
            if (index == 1) {
                System.out.println("No employee found.");
                return;
            }

            // Ask the user to select a employees
            System.out.print("Select the employee number: ");
            int choice = input.nextInt();

            // Retrieve the ID of the selected employees
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedEmpId = rs.getInt("E_ID");
                    break;
                }
                index++;
            }

            // Store the selected employees's ID in a variable
            System.out.println("Selected employees name: " + rs.getString("E_NAME"));
            emp_id=selectedEmpId;

        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to add teacher
    public void add(Teacher teach) {
         PreparedStatement checkEmployeeStmt = null;
        PreparedStatement insertEmployeeStmt = null;
        ResultSet rs = null;
        int employeeId = -1;
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            //step 1 to check if the teacher is in the employees table   
            String checkEmployeeSql = "SELECT E_ID FROM EMPLOYEES WHERE E_NAME = ?";
            checkEmployeeStmt = connection.prepareStatement(checkEmployeeSql);
            checkEmployeeStmt.setString(1, teach.name);
            rs = checkEmployeeStmt.executeQuery();

            if (rs.next()) {
                // Employee exists, get the employee_id
                employeeId = rs.getInt("E_ID");
                System.out.println("Employee found with ID: " + employeeId);
            } else {
                System.out.println("Ops!, this teacher is't in the employees table.");
                System.out.println("We will add him as an employee.");
                Employee emp_teach=new Employee();
                        //sends the value from object teach to empolyee
                        emp_teach.setName(teach.name);
                        emp_teach.setPhone_num(teach.phone_num);
                        emp_teach.setGender(teach.gender);
                        emp_teach.setAddress(teach.address);
                        emp_teach.setSal(teach.sal);
                        emp_teach.setJob("TEACHER");
                        emp_teach.setDept_id();
                        //FOR CONNECTION
                        emp_teach.add(emp_teach);
                        teach.setEmp_id();

            }

                
                
            //step 2 inserting information    
            String sql = "INSERT INTO TEACHER ( T_NAME, T_PHONE, SAL, ADDRESS, SHIFT ,EMP_ID) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, teach.name);
            statement.setInt(2, teach.phone_num);
            statement.setInt(3, teach.sal);
            statement.setString(4, teach.address);
            statement.setString(5, teach.shift);
            statement.setInt(6, teach.emp_id);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new teacher added.");
            }

        }
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    
    
    //to veiw teacher
    public void veiw() {
        
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rsulte=stm.executeQuery("SELECT * FROM TEACHER");
        while(rsulte.next()) {
                 System.out.println(rsulte.getDouble(1)+"  "+rsulte.getString(2)+"  "+rsulte.getDouble(3)+"  "+rsulte.getInt(4)+"  "+rsulte.getString(5)+"  "+rsulte.getString(6)+"  "+rsulte.getInt(7));
            }
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    

    }

    
     
    //to edit teacher
    public void edit(String name) {
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
              // SQL query to find the teacher by name
            String searchSql = "SELECT * FROM TEACHER WHERE T_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any teacher were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No teacher found with names similar to: " + name);
                return;
            }

            // Display the results and ask the user to confirm the student
            System.out.println("teachers found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                
                int teacherId = resultSet.getInt("T_ID");
                String teacherName = resultSet.getString("T_NAME");
                System.out.println(index + ". " + teacherName + " (ID: " + teacherId + ")");
                ids[index - 1] = teacherId;  // Store the ID in the array
                index++;
            }

            
            // Ask the user which student to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the teacher  you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Salary");
            System.out.println("4. Shift");
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
                    updateSql = "UPDATE TEACHER SET T_NAME = ? WHERE T_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the new phone number:");
                    int newPhone = scanner.nextInt();
                    setPhone_num(newPhone);
                    updateSql = "UPDATE TEACHER SET T_PHONE = ? WHERE T_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newPhone);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 3:
                    System.out.println("Enter the new salary:");
                    int newSal = scanner.nextInt();
                    setSal(newSal);
                    updateSql = "UPDATE TEACHER SET SAL = ?  WHERE T_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newSal);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 4:
                    System.out.println("Enter the new Shift (PM/AM):");
                    String newShift = scanner.next();
                    setShift(newShift);
                    updateSql = "UPDATE TEACHER SET SHIFT = ? WHERE T_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newShift);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 5:
                    System.out.println("Enter the new address:");
                    scanner.nextLine();  // Consume newline
                    String newAddress = scanner.nextLine();
                    setAddress(newAddress);
                    updateSql = "UPDATE TEACHER SET ADDRESS = ? WHERE T_ID = ?";
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
                System.out.println("Teacher with ID " + selectedId + " has been updated.");
            } 
            else {
                System.out.println("Update failed.");
            }
            

            } 
        
        catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to delete teacher
    public void delete(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // SQL query to find all teacher with names similar to the input
            String search_delt = "SELECT T_ID, T_NAME FROM TEACHER WHERE T_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any teacher were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No teacher found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("teachers found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int teacherId = resultSet.getInt("T_ID");
                String teacherName = resultSet.getString("T_NAME");
                System.out.println(index + ". " + teacherName + " (ID: " + teacherId + " )");
                ids[index - 1] = teacherId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to delete
            System.out.println("Enter the number of the teacher you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected student based on the ID
            String deleteSql = "DELETE FROM TEACHER WHERE T_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("teacher with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to search teacher
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM TEACHER WHERE T_NAME LIKE ?";
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
