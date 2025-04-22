
package skills;

import java.util.Scanner;
import java.sql.*;


public class Coursses {
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    
    
    //variable
    private int id;
    private String name;
    private int hours;
    private String start;
    private String end;
    private int teacher_id;
    private int class_id;
    private int dept_id;



    
    //obhect for input
    Scanner input=new Scanner (System .in);

    
    //capsolition
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        while(true){
            if(name.matches("[a-zA-Z0-9\\s]+")){
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
         while(true){
            if(hours==40){
                this.hours = hours;
                break;
            }
            
            else{
                System.out.println("syntax error");
                System.out.println("Enter again: ");
                hours =input.nextInt();
                setHours(hours);
            
            }
                
        }
        
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        while(true){
            if(start.matches("[0-9/-]+[0-9]+")){
                this.start = start;
                break;
            }
            
            else{
                System.out.println("syntax error");
                System.out.println("Enter again: ");
                start =input.nextLine();
                setStart(start);
            
            }
                
        }
        
    }

    
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
         while(true){
            if(end.matches("[0-9/-]+[0-9]+")){
                this.end = end;
                break;
            }
            
            else{
                System.out.println("syntax error");
                System.out.println("Enter again: ");
                end =input.nextLine();
                setStart(end);
            
            }
                
        }
       
    }

    
    
    
    
    //set the teacher for the course.
    public void setTeacher_id()  {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedTeacherId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all teachers from the database
            String sql = "SELECT T_ID, T_NAME FROM TEACHER";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            // Display all teachers to the user
            System.out.println("Available teachers: ");
            int index = 1;
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("T_NAME"));
                index++;
            }

            
            // If no results found
            if (index == 1) {
                System.out.println("No teachers found.");
                return;
            }

            
            // Ask the user to select a teacher
            System.out.print("Select the teacher number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected teacher
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedTeacherId = rs.getInt("T_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected teacher's ID in a variable
            System.out.println("Selected teacher name: " + rs.getString("T_NAME"));
            teacher_id=selectedTeacherId;
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        
    }
       

    //set the class for the course.    
    public void setClass_id() {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedClass = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all class from the database
            String sql = "SELECT CLA_ID, CLA_NAME ,CLA_FLOOR FROM CLASS";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            // Display all class to the user
            System.out.println("Available classes: ");
            int index = 1;
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("CLA_NAME") + rs.getString("CLA_FLOOR"));
                index++;
            }

            
            // If no results found
            if (index == 1) {
                System.out.println("No classes found.");
                return;
            }

            
            // Ask the user to select a class
            System.out.print("Select the class number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected class
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedClass = rs.getInt("CLA_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected class's ID in a variable
            System.out.println("Selected class name: " + rs.getString("CLA_NAME"));
            class_id=selectedClass;

        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
    
    
    //set the depatment for the course.    
    public void setDept_id() {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedDepartment = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all class from the database
            String sql = "SELECT D_ID, D_NAME  FROM DEPARTMENT";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            // Display all class to the DEPARTMENT
            System.out.println("Available department: ");
            int index = 1;
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("D_NAME") );
                index++;
            }

            
            // If no results found
            if (index == 1) {
                System.out.println("No department found.");
                return;
            }

            
            // Ask the user to select a department
            System.out.print("Select the department number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected department
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedDepartment = rs.getInt("D_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected teacher's ID in a variable
            System.out.println("Selected department name: " + rs.getString("D_NAME"));
            dept_id=selectedDepartment;

        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
    
    
    //the operation
    
    //to add Coursses
    public void add(Coursses cor) {
        
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            String sql = "INSERT INTO COURSE ( C_NAME, C_HOURS, START_DATE, END_DATE, TEACHER_ID , CLASS_ID, DEPT_ID) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, cor.name);
            statement.setInt(2, cor.hours);
            statement.setString(3, cor.start);
            statement.setString(4, cor.end);
            statement.setInt(5, cor.teacher_id);
            statement.setInt(6, cor.class_id);
            statement.setInt(7, cor.dept_id);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new course added.");
            }

        }
        
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            
        
    }


    //to veiw Coursses
    public void veiw() {
        
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rs=stm.executeQuery("select * from course");
        
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getInt(6)+"  "+rs.getInt(7)+"  "+rs.getInt(8));
            }
        
        }
        
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    
    //to edit Coursses
    public void edit(String name) {
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
              // SQL query to find the courses by name
            String searchSql = "SELECT * FROM COURSE WHERE C_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any courses were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No courses found with names similar to: " + name);
                return;
            }
            
            // Display the results and ask the user to confirm the student
            System.out.println("Courses found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int courseId = resultSet.getInt("C_ID");
                String courseName = resultSet.getString("C_NAME");
                System.out.println(index + ". " + courseName +" (ID: " + courseId + ")");
                ids[index - 1] = courseId;  // Store the ID in the array
                index++;
            }

            
            // Ask the user which course to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the course you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Hours");
            System.out.println("3. Start date");
            System.out.println("4. End date");
            System.out.println("5. Teacher ID");
            System.out.println("6. Class ID");
            System.out.println("7. Department ID");
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
                    updateSql = "UPDATE COURSE SET C_NAME = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the hours:");
                    int newHours = scanner.nextInt();
                    setHours(newHours);
                    updateSql = "UPDATE COURSE SET C_Hours = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newHours);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 3:
                    System.out.println("Enter the new start date:");
                    input.nextLine();// Consume newline
                    String newStart_date = scanner.nextLine();
                    setStart(newStart_date);
                    updateSql = "UPDATE COURSE SET START_DATE = ? WHERE C_ID =  ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newStart_date);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 4:
                    System.out.println("Enter the new end date:");
                    input.nextLine();// Consume newline
                    String newEnd_date = scanner.nextLine();
                    setEnd(newEnd_date);
                    updateSql = "UPDATE COURSE SET END_DATE = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newEnd_date);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 5:
                    setTeacher_id();
                    updateSql = "UPDATE COURSE SET TEACHER_ID = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, teacher_id);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 6:
                    setClass_id();
                    updateSql = "UPDATE COURSE SET CLASS_ID = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, class_id);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 7:
                    setDept_id();
                    updateSql = "UPDATE COURSE SET DEPT_ID = ? WHERE C_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, dept_id);
                    updateStatement.setInt(2, selectedId);
                    break;    
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            // Execute the update statement
            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course with ID " + selectedId + " has been updated.");
            } else {
                System.out.println("Update failed.");
            }
            

            } 
        
        catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
 
    
    //to delete Coursses
    public void delete(String name) {
        
         try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // SQL query to find all students with names similar to the input
            String search_delt = "SELECT C_ID, C_NAME FROM COURSE WHERE C_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any students were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No courses found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("Courses found: ");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int courseId = resultSet.getInt("C_ID");
                String courseName = resultSet.getString("C_NAME");
                System.out.println(index + ". " + courseName + " (ID: " + courseId + " )");
                ids[index - 1] = courseId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to delete
            System.out.println("Enter the number of the course you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected student based on the ID
            String deleteSql = "DELETE FROM COURSE WHERE C_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("course with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }
    
    
    
    //to search Coursses
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM COURSE WHERE C_NAME LIKE ?";
            PreparedStatement statement = connection.prepareStatement(search_stu);

            // Set the keyword to search for in title or genre
            statement.setString(1, "%" + name + "%");

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            while(resultSet.next()) {
                 System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getInt(3)+"  "+resultSet.getString(4)+"  "+resultSet.getString(5)+"  "+resultSet.getInt(6)+"  "+resultSet.getInt(7)+"  "+resultSet.getInt(8));
            }
        } 
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    
}
