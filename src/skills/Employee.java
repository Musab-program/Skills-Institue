
package skills;
import java.sql.*;
import java.util.Scanner;

public class Employee extends Person{
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    //Variable
    protected String job;
    protected int sal;
    private int birth ;
    protected int dept_id;

    //capsolaton
    public String getJob() {
        return job;
    }
    
    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth =birth;
    }

    public void setJob(String job) {
        while(true){
            if (job.matches("[a-zA-Z\\s]+")){
                this.job = job;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                job=input.nextLine();
                setJob(job);
            }
        }
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
         while(true){
            if (sal >=20000 && sal <=100000){
                this.sal = sal;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                sal=input.nextInt();
                setSal(sal);
            }
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
    
    
    
    //to add employees
    public void add(Employee emp) {
        
        
                    try (Connection connection = DriverManager.getConnection(url, username, password)) 
            {
            String sql = "INSERT INTO EMPLOYEES ( E_NAME, E_PHONE, BIRTH, JOB, SAL , ADDRESS ,DEPT_ID) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement.setString(1, emp.name);
            statement.setInt(2, emp.phone_num);
            statement.setInt(3, emp.birth);
            statement.setString(4, emp.job);
            statement.setInt(5, emp.sal);
            statement.setString(6, emp.address);
            statement.setInt(7, emp.dept_id);
           
            
            // Execute the insert statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employees added.");
            }

        
            
            //to insert into work in class
            
            String sql_work = "INSERT INTO WORK_IN ( EMP_ID, DEPT_ID, JOB) VALUES (  ?, ?, ?)";
            PreparedStatement statement_work = connection.prepareStatement(sql_work);
            
            
            // استخدام القيم التي أدخلها المستخدم
            statement_work.setInt(1,id);
            statement_work.setInt(2, emp.dept_id);
            statement_work.setString(3, emp.job);


        }
            catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
            
    }


    
    //to veiw employees
    public void veiw( ) {
        
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rs=stm.executeQuery("select * from employees");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getInt(4)+"  "+rs.getString(5)+"  "+rs.getInt(6)+"  "+rs.getString(7)+"  "+rs.getInt(8));
        }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    
    
    //to edit employees
    public void edit(String name) {
        
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            
             // SQL query to find the employees by name
            String searchSql = "SELECT * FROM EMPLOYEES WHERE E_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(searchSql);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the result
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any employees were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No employees found with names similar to: " + name);
                return;
            }
            
            // Display the results and ask the user to confirm the student
            System.out.println("Employees found:");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("E_ID");
                String employeeName = resultSet.getString("E_NAME");
                System.out.println(index + ". " + employeeName +" (ID: " + employeeId + ")");
                ids[index - 1] = employeeId;  // Store the ID in the array
                index++;
            }

            
            // Ask the user which course to update
            System.out.println("--------------------------------------------");
            System.out.println("Enter the number of the employee you want to update:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID
            int selectedId = ids[choice - 1];

            // Ask the user what they want to update
            System.out.println("What would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Phone");
            System.out.println("3. Birth Year");
            System.out.println("4. Job");
            System.out.println("5. Salary");
            System.out.println("6. Address");
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
                    updateSql = "UPDATE EMPLOYEES SET E_NAME = ? WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newName);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 2:
                    System.out.println("Enter the new phone number:");
                    int newPhone = scanner.nextInt();
                    setPhone_num(newPhone);
                    updateSql = "UPDATE EMPLOYEES SET E_PHONE = ? WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newPhone);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 3:
                    System.out.println("Enter the new birth year:");
                    int newBirthYear = scanner.nextInt();
                    updateSql = "UPDATE EMPLOYEES SET BIRTH = ? WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newBirthYear);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 4:
                    System.out.println("Enter the job: ");
                    input.nextLine();// Consume newline
                    String newJob = scanner.nextLine();
                    setJob(newJob);
                    updateSql = "UPDATE EMPLOYEES SET JOB = ? WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newJob);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 5:
                    System.out.println("Enter the new salary:");
                    int newSal = scanner.nextInt();
                    setSal(newSal);
                    updateSql = "UPDATE EMPLOYEES SET SAL = ?  WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newSal);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 6:
                    System.out.println("Enter the new address:");
                    scanner.nextLine();  // Consume newline
                    String newAddress = scanner.nextLine();
                    setAddress(newAddress);
                    updateSql = "UPDATE EMPLOYEES SET ADDRESS = ? WHERE E_ID = ?";
                    updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setString(1, newAddress);
                    updateStatement.setInt(2, selectedId);
                    break;
                case 7:
                    setDept_id();
                    updateSql = "UPDATE EMPLOYEES SET DEPT_ID = ? WHERE E_ID = ?";
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
                System.out.println("Employee with ID " + selectedId + " has been updated.");
            } else {
                System.out.println("Update failed.");
            }
            

            } 
        
        catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //to delete employees
    public void delete(String name) {
        
       try ( Connection connection = DriverManager.getConnection(url, username, password)) {
            
            // SQL query to find all employees with names similar to the input
            String search_delt = "SELECT E_ID, E_NAME FROM EMPLOYEES WHERE E_NAME LIKE ?";
            PreparedStatement searchStatement = connection.prepareStatement(search_delt);
            searchStatement.setString(1, "%" + name + "%");

            // Execute the query and store the results
            ResultSet resultSet = searchStatement.executeQuery();

            // Check if any employees were found
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No employees found with names similar to: " + name);
                return;
            }

            // Display the results to the user
            System.out.println("Employees found: ");
            int index = 1;
            int[] ids = new int[100]; // Array to store IDs (assuming max 100 results)
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("E_ID");
                String employeeName = resultSet.getString("E_NAME");
                System.out.println(index + ". " + employeeName + " (ID: " + employeeId + " )");
                ids[index - 1] = employeeId;  // Store the ID in the array
                index++;
            }

            // Ask the user which student to delete
            System.out.println("Enter the number of the employee you want to delete:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            // Get the selected student's ID from the array
            int selectedId = ids[choice - 1];

            // SQL query to delete the selected student based on the ID
            String deleteSql = "DELETE FROM EMPLOYEES WHERE E_ID = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
            deleteStatement.setInt(1, selectedId);

            // Execute the delete statement
            deleteStatement.executeUpdate();

            // Print success message
            System.out.println("Employee with ID " + selectedId + " has been deleted.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } 
        
        
    }
    
    
    
    //to search employees
    public void search(String name) {
        
        try ( Connection connection = DriverManager.getConnection(url, username, password)) 
        {
            String search_stu = "SELECT * FROM EMPLOYEES WHERE E_NAME LIKE ?";
            PreparedStatement statement = connection.prepareStatement(search_stu);

            // Set the keyword to search for in title or genre
            statement.setString(1, "%" + name + "%");

            // Execute the select statement
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            while(resultSet.next())
        {
            System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+"  "+resultSet.getInt(3)+"  "+resultSet.getInt(4)+"  "+resultSet.getString(5)+"  "+resultSet.getInt(6)+"  "+resultSet.getString(7)+"  "+resultSet.getInt(8));
        }
        } 
        
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
