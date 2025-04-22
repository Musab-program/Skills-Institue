
package helper_class;

import java.sql.*;
import java.util.Scanner;


public class WorkIn {
    
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    
    //variables
    private int dept_id;
    private int emp_id;
    private String job;
    
    //capsloation

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    
    
    
    //select the object
    public void setDept_id(String dept_name) {
        
//        this.dept_id = dept_id;
    }

    
    public void setEmp_id(String emp_name) {
        
//        this.emp_id = emp_id;
    }
    
    
    
    //the operation
    
    //query for department's employee
    public void query_employee(String employeeName){
            
        Connection conn = null;
        PreparedStatement deptStatement = null;
        PreparedStatement employeeStatement = null;
        ResultSet deptResultSet = null;
        ResultSet employeeResultSet = null;
        Scanner scanner = new Scanner(System.in);
        int deptId_query = -1;
        String name_query_result ="";

        
        try {
            // Connect to Oracle database
            conn = DriverManager.getConnection(url, username, password);

            
            // Step 1: Find teachers with similar names
            String findDeptSql = "SELECT d.d_id, d.d_name FROM department d WHERE d.d_name LIKE ?";
            deptStatement = conn.prepareStatement(findDeptSql ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            deptStatement.setString(1, "%" + employeeName + "%");
            deptResultSet = deptStatement.executeQuery();

            
            // Step 2: Display the list of similar teacher names
            System.out.println("Department found:");
            int index = 1;
            while (deptResultSet.next()) {
                int id_query_result = deptResultSet.getInt("d_id");
                name_query_result = deptResultSet.getString("d_name");
                System.out.println(index + ". " + name_query_result + " (ID: " + id_query_result + ")");
                index++;
                
            }

            // Step 3: Ask user to select a teacher by index
            System.out.print("Please select a department by index: ");
            int selectedIndex = scanner.nextInt();
            deptResultSet.absolute(selectedIndex);  // Move cursor to the selected row
            deptId_query = deptResultSet.getInt("d_id");
            System.out.println("Selected department ID: " + deptId_query);
            

            // Step 4: Use the teacher ID to find students who study with this teacher
            String findStudentsSql = "select e.e_id ,e.e_name , w.job  from  work_in w join employees e  on w.emp_id = e.e_id where w.dept_id =?";
            employeeStatement = conn.prepareStatement(findStudentsSql);
            employeeStatement.setInt(1, deptId_query);
            employeeResultSet = employeeStatement.executeQuery();

            
            // Step 5: Display the students studying with the selected teacher
            System.out.println("Employees working in the department "+name_query_result);
            while (employeeResultSet.next()) {
                
                int empId = employeeResultSet.getInt("e_id");
                String empName = employeeResultSet.getString("e_name");
                String type_work=employeeResultSet.getString("job");
                System.out.println("Student => ID: " + empId + ", Name: " + empName + " teach " + type_work);
            
            }

        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            
            
        } finally {
            // Close resources
            try {
                if (deptResultSet != null) deptResultSet.close();
                if (employeeResultSet != null) employeeResultSet.close();
                if (deptStatement != null) deptStatement.close();
                if (employeeStatement != null) employeeStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
    
    

    
}