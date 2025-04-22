
package skills;

import java.util.Scanner;
import java.sql.*;

public class Dept {
    
    private String url = "jdbc:oracle:thin:@localhost:1522/orcll";
    private String username = "skills";
    private String password = "skills";
    //variable
    private int id;
    private String name;
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
            if(name.matches("[a-zA-Z\\s]+")){
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
    
   
    
    
    
    //to veiw Dept
    public void veiw() {
     
        try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stm=connection.createStatement();
        ResultSet rs=stm.executeQuery("select * from department");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+"  "+rs.getString(2));
        }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    
        
    }
    

    
    
}
