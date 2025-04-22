package skills;
import java.util.Scanner;
import java.sql.*;
import helper_class.*;


public class Skills {


    public static void main(String[] args) {
        //viarable
        String name;
        int phone_num ;
        String gender;
        String address;
        int hours;
        String start;
        String end;
        int choice1;
        int choice2;
        int floor;
        
        //object for input
         Scanner input = new Scanner (System.in); 

     //the operation    
    while(true){
        System.out.println(true);
        System.out.println("---------------------------------");
        System.out.println("Student => 1");
        System.out.println("Teacher => 2");
        System.out.println("Employee  => 3");
        System.out.println("Course => 4");
        System.out.println("Class => 5");
        System.out.println("Department => 6");
        System.out.println("Student famliy => 7");
        System.out.println("Study in => 8");
        System.out.println("Work in => 9");
        System.out.println("Exist => 10");
        System.out.println("Enter your choice");

        choice1 = input.nextInt();
        //student
        if (choice1 == 1){
            OUTER:
            while (true) {
                //object
                Student stu_add = new Student();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the student name: ");
                        name = input.nextLine();
                        stu_add.setName(name);
                        System.out.println("Enter the student phone number: ");
                        phone_num = input.nextInt();
                        stu_add.setPhone_num(phone_num);
                        input.nextLine();
                        System.out.println("Enter male or famle: ");
                        gender = input.nextLine();
                        stu_add.setGender(gender);
                        System.out.println("Enter the student address: ");
                        address = input.nextLine();
                        stu_add.setAddress(address);
                        System.out.println("Enter the student's birth year: ");
                        int birth = input.nextInt();
                        stu_add.setBirth(birth);
                        stu_add.add(stu_add);
                        input.nextLine();
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the student name: ");
                        name = input.nextLine();
                        stu_add.delete(name);
                        break;
                        
                    case 3:
                        stu_add.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the student name: ");
                        name = input.nextLine();
                        stu_add.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the student name: ");
                        name = input.nextLine();
                        stu_add.search(name);
                        break;
                        
                    case 6:
                        break;
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
        //teacher
        else if (choice1 == 2){
            OUTER:
            while (true) {
                Teacher teach_add = new Teacher();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the teacher name: ");
                        name = input.nextLine();
                        teach_add.setName(name);
                        System.out.println("Enter the teacher phone number: ");
                        phone_num = input.nextInt();
                        teach_add.setPhone_num(phone_num);
                        input.nextLine();
                        System.out.println("Enter male or female: ");
                        gender = input.nextLine();
                        teach_add.setGender(gender);
                        System.out.println("Enter the teacher address: ");
                        address = input.nextLine();
                        teach_add.setAddress(address);
                        System.out.println("Enter the teacher shift PM or AM: ");
                        String shift = input.nextLine();
                        teach_add.setShift(shift);
                        teach_add.setJob("teacher");
                        System.out.println("Enter the teacher salary: ");
                        int sal = input.nextInt();
                        teach_add.setSal(sal);
                        
                        teach_add.add(teach_add);
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the teacher name: ");
                        name = input.nextLine();
                        teach_add.delete(name);
                        break;
                        
                    case 3:
                        teach_add.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the teacher name: ");
                        name = input.nextLine();
                        teach_add.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the teacher name: ");
                        name = input.nextLine();
                        teach_add.search(name);
                        break;
                    
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
              break;  
            }
            
            }
        
        
        //employees
        else if (choice1 == 3){
            OUTER:
            while (true) {
                Employee emp_add = new Employee();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the employee name: ");
                        name = input.nextLine();
                        emp_add.setName(name);
                        System.out.println("Enter the employee phone number: ");
                        phone_num = input.nextInt();
                        emp_add.setPhone_num(phone_num);
                        input.nextLine();
                        System.out.println("Enter male or female: ");
                        gender = input.nextLine();
                        emp_add.setGender(gender);
                        System.out.println("Enter the employee address: ");
                        address = input.nextLine();
                        emp_add.setAddress(address);
                        System.out.println("Enter the employee job: ");
                        String job = input.nextLine();
                        emp_add.setJob(job);
                        System.out.println("Enter the employee salary: ");
                        int sal = input.nextInt();
                        emp_add.setSal(sal);
                        input.nextLine();
                        emp_add.setDept_id();
                        emp_add.add(emp_add);
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the employee name: ");
                        name = input.nextLine();
                        emp_add.delete(name);
                        break;
                        
                    case 3:
                        emp_add.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the employee name: ");
                        name = input.nextLine();
                        emp_add.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the employee name: ");
                        name = input.nextLine();
                        emp_add.search(name);
                        break;
                        
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
    
        //course
        ///////////////////////////////////////////////////////////////////////////العصيمي
        else if (choice1 == 4){
            OUTER:
            while (true) {
                //object
                Coursses cor_add = new Coursses();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5"); 
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation: ");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the Coursse name: ");
                        name = input.nextLine();
                        cor_add.setName(name);
                        System.out.println("Enter the Coursse hours: ");
                        hours = input.nextInt();
                        cor_add.setHours(hours);
                        input.nextLine();
                        System.out.println("Enter the Coursse start date(s): ");
                        start = input.nextLine();
                        cor_add.setStart(start);
                        System.out.println("Enter the Coursse end date(s): ");
                        end = input.nextLine();
                        cor_add.setEnd(end);
                        //function to select the teacher id 
                        cor_add.setTeacher_id();
                        //function to select the class id 
                        cor_add.setClass_id();
                        //function to select the class id 
                        cor_add.setDept_id();
                        cor_add.add(cor_add);
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the Coursse name: ");
                        name = input.nextLine();
                        cor_add.delete(name);
                        break;
                        
                    case 3:
                        cor_add.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the Coursse name: ");
                        name = input.nextLine();
                        cor_add.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the Coursse name: ");
                        name = input.nextLine();
                        cor_add.search(name);
                        break;
                        
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
        
        
        //class
        else if (choice1 == 5){
            OUTER:
            while (true) {
                Classes cla_add = new Classes();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the class name: ");
                        name = input.nextLine();
                        cla_add.setName(name);
                        System.out.println("Enter the class floor: ");
                        floor = input.nextInt();
                        cla_add.setFloor(floor);
                        cla_add.add(cla_add);
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the class name: ");
                        name = input.nextLine();
                        cla_add.delete(name);
                        break;
                        
                    case 3:
                        cla_add.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the class nama: ");
                        name = input.nextLine();
                        cla_add.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the class nama: ");
                        name = input.nextLine();
                        cla_add.search(name);
                        break;
                        
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
        //department
        else if (choice1 == 6){
            OUTER:
            while (true) {
                Dept dept=new Dept();
                System.out.println("---------------------------------");
                System.out.println("View  => 1");
                System.out.println("Back step => 2");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                switch (choice2) {         
                    case 1:
                        dept.veiw();
                        break;
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
              }
            
            }
        
        
        //Student famliy
        else if (choice1 == 7){
            OUTER:
            while (true) {
                Stu_family member = new Stu_family();
                System.out.println("---------------------------------");
                System.out.println("Add => 1");
                System.out.println("Delete => 2");
                System.out.println("View  => 3");
                System.out.println("Edit => 4");
                System.out.println("Search => 5");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the member name: ");
                        name = input.nextLine();
                        member.setName(name);
                        System.out.println("Enter the member phone number: ");
                        phone_num = input.nextInt();
                        member.setPhone_num(phone_num);
                        input.nextLine();
                        System.out.println("Enter male or famle: ");
                        gender = input.nextLine();
                        member.setGender(gender);
                        System.out.println("Enter the member address: ");
                        address = input.nextLine();
                        member.setAddress(address);
                        System.out.println("Enter the type relation: ");
                        String type = input.nextLine();
                        member.setRelation(type);
                        //set student id
                        member.setStu_id();
                        member.add(member);
                        input.nextLine();
                        break OUTER;
                        
                    case 2:
                        System.out.println("Enter the member name: ");
                        name = input.nextLine();
                        member.delete(name);
                        break;
                        
                    case 3:
                        System.out.println("Enter the member name: ");
                        name = input.nextLine();
                        member.veiw();
                        break;
                        
                    case 4:
                        System.out.println("Enter the member name: ");
                        name = input.nextLine();
                        member.edit(name);
                        break;
                        
                    case 5:
                        System.out.println("Enter the member name: ");
                        name = input.nextLine();
                        member.search(name);
                        break;
                        
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
        
        //study in 
        else if (choice1 == 8){
            OUTER:
            while (true) {
                StudyIn study = new StudyIn();
                System.out.println("---------------------------------");
                System.out.println("Select course for Student => 1");
                System.out.println("Select student's courses => 2");
                System.out.println("Select teacher's students => 3");
                System.out.println("Select course 's students => 4");
                System.out.println("Back step => 6");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                input.nextLine();
                switch (choice2) {
                    case 1:
                        System.out.println("Enter the Student name: ");
                        name = input.nextLine();
                        study.setStudent_id(name);
                        System.out.println("Enter the course name: ");
                        name = input.nextLine();
                        study.setCourse_id(name);
                        break;                         
                    case 2:
                        System.out.println("Enter the Student name to find out his courses: ");
                        name = input.nextLine();
                        study.set_Query_student(name);
                        break;                       
                    case 3:
                        System.out.println("Enter the teacher name: ");
                        name = input.nextLine();
                        study.query_teacher(name);
                        break;                  
                    case 4:
                        System.out.println("Enter the class nama: ");
                        name = input.nextLine();
                        study.query_course( name);
                        break;                   
                    case 6:
                        break;    
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
            }
            
            }
        
        
        //work  in 
        else if (choice1 == 9){
            OUTER:
            while (true) {
                WorkIn work= new WorkIn();
                System.out.println("---------------------------------");
                System.out.println("Select department's employees => 1");
                System.out.println("Back step => 2");
                System.out.println("Enter the number of operation");
                choice2 = input.nextInt();
                switch (choice2) {         
                    case 1:
                        System.out.println("Enter the deparment name: ");
                        input.nextLine();
                        name = input.nextLine();
                        work.query_employee( name);
                        break;                
                    case 2:
                        break;
                        
                    default:
                        System.out.println("This number is't in the list");
                }
                break;
              }
            
            }
        
        
        //exist
        else if (choice1 ==10){
            break;
        }
        
        
    }
    }
    
    
    
    
    }
    

