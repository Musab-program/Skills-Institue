
package helper_class;

import java.sql.*;
import java.util.Scanner;
import skills.Skills;


public class StudyIn {
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    //variable
    private int teacher_id;
    private int student_id;
    private int course_id;
    private String time;
    Scanner input=new Scanner(System.in);
    
    //capsolation
    public String getTime() {
        return time;
    }

    
    
    //the operation
    public void setTime(String time) {
        this.time = time;
    }


    
    
    //set student
    public void setStudent_id( String name) {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedStudentId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
           
         // Retrieve all teachers from the database
            String sql = "SELECT S_ID, S_NAME FROM STUDENT WHERE S_NAME LIKE ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

           
            
            // Display all Students to the user
            
            int index = 1;
            
            
            // If no results found
            if (!rs.next()) {
                System.out.println("No Students found with similar to name: " + name);
                return;
            }

            else {
                System.out.println("Available Students: ");

            }
            
            
            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("S_NAME"));
                index++;
                }
            
            // Ask the user to select a Students
            System.out.print("Select the Student number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected Students
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedStudentId = rs.getInt("S_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected teacher's ID in a variable
            System.out.println("Selected Student name: " + rs.getString("S_NAME"));
            student_id=selectedStudentId;
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
    }

   
    
    
    // select the TEACHER
    public void setTeacher_id(int id) {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedTeacherId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all teachers from the database
            String sql = "SELECT T_ID, T_NAME FROM TEACHER WHERE T_ID = ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

  
            
                 // Check if a result exists
            if (rs.next()) {
                // Retrieve the teacher ID and name
                selectedTeacherId = rs.getInt("T_ID");
                String teacherName = rs.getString("T_NAME");

                // Store the selected teacher's ID in a variable
                System.out.println("The course's teacher is: " + teacherName);
                teacher_id = selectedTeacherId;
                
            } 
            
            else {
                System.out.println("No teacher found with ID: " + id);
            }          
            
            
            
        } 
       
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
    }
    
    
    
    //set course
    public void setCourse_id(String name ) {
        
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedCourseId = -1;
        int selectedteacherId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all courses from the database
            String sql = "SELECT C_ID, C_NAME , TEACHER_ID FROM COURSE WHERE C_NAME LIKE ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            
            int index = 1;
            
            // If no results found
            if (!rs.next()) {
                System.out.println("No courses found.");
                return;
            }
            
            else{
                // Display all courses to the user
                System.out.println("Available courses: ");
                
            }

            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("C_NAME"));
                index++;
                }
            
            
            // Ask the user to select a Students
            System.out.print("Select the course number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected Students
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedCourseId = rs.getInt("C_ID");
                    selectedteacherId = rs.getInt("TEACHER_ID");
                    break;
                }
                
                
                index++;
            }

            
            // Store the selected course's ID in a variable
            System.out.println("Selected course name: " + rs.getString("C_NAME"));
            course_id=selectedCourseId;
            
            
            //set the teacher for course
            setTeacher_id( selectedteacherId);
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

       
    }
    
    
    
    
    //query for courses studed by student
    public void set_Query_student(String name){
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int selectedStudentId = -1;
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all teachers from the database
            String sql = "SELECT S_ID, S_NAME FROM STUDENT WHERE S_NAME LIKE ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            int index = 1;
            
            
            // If no results found
            if (!rs.next()) {
                System.out.println("No Students found.");
                return;
            }

            else {
                // Display all Students to the user
            
                System.out.println("Available Students: ");
                
            }
            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(index + ". " + rs.getString("S_NAME"));
                index++;
                }
            
            // Ask the user to select a Students
            System.out.print("Select the Student number: ");
            int choice = input.nextInt();

            
            // Retrieve the ID of the selected Students
            rs.beforeFirst();  // Move cursor to the start of the result set
            index = 1;
            while (rs.next()) {
                if (index == choice) {
                    selectedStudentId = rs.getInt("S_ID");
                    break;
                }
                index++;
            }

            
            // Store the selected teacher's ID in a variable
            System.out.println("Selected Student : " + rs.getString("S_NAME"));
            student_id=selectedStudentId;
            set_Query_course(student_id);
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
       
       
    }
      
    
    
     //query for courses studed by student
    public void set_Query_course(int student_id){
        
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String selectedTeacherTime = "";
        
        
       try (Connection connection = DriverManager.getConnection(url, username, password)){
            
         // Retrieve all teachers from the database
            String sql = "select c.c_name , s.time from  study_in s join course c  on s.course_id = c.c_id where s.stu_id = ?";
            pstmt = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1, student_id);
            rs = pstmt.executeQuery();
  
           
            
            int index=1;
            while (rs.next()) {
                // Retrieve the teacher ID and name
                selectedTeacherTime = rs.getString("time");
                String teacherName = rs.getString("c_name");

                // Store the selected teacher's ID in a variable
                System.out.println("The course is: " + teacherName + " from " +selectedTeacherTime );
                index++;
                }
            
        } 
       
       catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        
//        this.teacher_id = teacher_id;
        
    }
    
    

    //query for teacher's students
    public void query_teacher(String teacherName){
            
        
        Connection conn = null;
        PreparedStatement teacherStatement = null;
        PreparedStatement studentStatement = null;
        ResultSet teacherResultSet = null;
        ResultSet studentResultSet = null;
        Scanner scanner = new Scanner(System.in);
        int teacherId_query = -1;
        String name_query_result ="";

        
        try {
            // Connect to Oracle database
            conn = DriverManager.getConnection(url, username, password);

            
            // Step 1: Find teachers with similar names
            String findTeacherSql = "SELECT t.t_id, t.t_name FROM teacher t WHERE t.t_name LIKE ?";
            teacherStatement = conn.prepareStatement(findTeacherSql ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            teacherStatement.setString(1, "%" + teacherName + "%");
            teacherResultSet = teacherStatement.executeQuery();

            
            // Step 2: Display the list of similar teacher names
            System.out.println("Teachers found:");
            int index = 1;
            while (teacherResultSet.next()) {
                int id_query_result = teacherResultSet.getInt("t_id");
                name_query_result = teacherResultSet.getString("t_name");
                System.out.println(index + ". " + name_query_result + " (ID: " + id_query_result + ")");
                index++;
                
            }

            // Step 3: Ask user to select a teacher by index
            System.out.print("Please select a teacher by index: ");
            int selectedIndex = scanner.nextInt();
            teacherResultSet.absolute(selectedIndex);  // Move cursor to the selected row
            teacherId_query = teacherResultSet.getInt("t_id");
            System.out.println("Selected teacher ID: " + teacherId_query);
            

            // Step 4: Use the teacher ID to find students who study with this teacher
            String findStudentsSql = "select st.s_id , st.s_name , s.time  from  study_in s join student st  on s.stu_id = st.s_id where s.teacher_id =?";
            studentStatement = conn.prepareStatement(findStudentsSql);
            studentStatement.setInt(1, teacherId_query);
            studentResultSet = studentStatement.executeQuery();

            
            // Step 5: Display the students studying with the selected teacher
            System.out.println("Students studying with the teacher "+name_query_result);
            while (studentResultSet.next()) {
                
                int studentId = studentResultSet.getInt("s_id");
                String studentName = studentResultSet.getString("s_name");
                String shift_studying=studentResultSet.getString("time");
                System.out.println("Student => ID: " + studentId + ", Name: " + studentName + " study from " + shift_studying);
            
            }

        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            
            
        } finally {
            // Close resources
            try {
                if (teacherResultSet != null) teacherResultSet.close();
                if (studentResultSet != null) studentResultSet.close();
                if (teacherStatement != null) teacherStatement.close();
                if (studentStatement != null) studentStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     
    
    
    //query for course's students
        public void query_course(String courseName){
            
        Connection conn = null;
        PreparedStatement courseStatement = null;
        PreparedStatement studentStatement = null;
        ResultSet coursseResultSet = null;
        ResultSet studentResultSet = null;
        Scanner scanner = new Scanner(System.in);
        int courseId_query = -1;
        String name_query_result ="";

        try {
            // Connect to Oracle database
            conn = DriverManager.getConnection(url, username, password);

            
            // Step 1: Find teachers with similar names
            String findTeacherSql = "SELECT c.c_id, c.c_name FROM course c WHERE c.c_name LIKE ?";
            courseStatement = conn.prepareStatement(findTeacherSql ,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            courseStatement.setString(1, "%" + courseName + "%");
            coursseResultSet = courseStatement.executeQuery();

            
            // Step 2: Display the list of similar teacher names
            System.out.println("Course found:");
            int index = 1;
            while (coursseResultSet.next()) {
                int id_query_result = coursseResultSet.getInt("c_id");
                name_query_result = coursseResultSet.getString("c_name");
                System.out.println(index + ". " + name_query_result + " (ID: " + id_query_result + ")");
                index++;
            }

            
            // Step 3: Ask user to select a teacher by index
            System.out.print("Please select a course by index: ");
            int selectedIndex = scanner.nextInt();
            coursseResultSet.absolute(selectedIndex);  // Move cursor to the selected row
            courseId_query = coursseResultSet.getInt("c_id");
            System.out.println("Selected course ID: " + courseId_query);

            // Step 4: Use the course ID to find students who study with this teacher
            String findStudentsSql = "select st.s_id ,st.s_name , s.time from  study_in s join student st  on s.stu_id = st.s_id where s.course_id =?";
            studentStatement = conn.prepareStatement(findStudentsSql);
            studentStatement.setInt(1, courseId_query);
            studentResultSet = studentStatement.executeQuery();

            
            // Step 5: Display the students studying with the selected teacher
            System.out.println("Students studying in course "+name_query_result);
            while (studentResultSet.next()) {
                int studentId = studentResultSet.getInt("s_id");
                String studentName = studentResultSet.getString("s_name");
                String shift_studying=studentResultSet.getString("time");
                System.out.println("Student => ID: " + studentId + ", Name: " + studentName + " study from " + shift_studying);
            }

        } 
        
        
        catch (SQLException e) {
            e.printStackTrace();
        } 
        
        finally {
            // Close resources
            try {
                if (coursseResultSet != null) coursseResultSet.close();
                if (studentResultSet != null) studentResultSet.close();
                if (courseStatement != null) courseStatement.close();
                if (studentStatement != null) studentStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            
        }
        
        


}
