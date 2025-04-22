
package skills;
import java.util.Scanner;

public  class  Person {
    //variable
    protected int id;
    protected String name;
    protected int phone_num ;
    protected String gender;
    protected String address;
    //object for input
    Scanner input=new Scanner(System.in);



    public int getId() {
        return id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
         while(true){
            if (name.matches("[a-zA-Z\\s]+")){
                this.name = name;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                name=input.nextLine();
                setName(name);
            }
        }
    }

    public int getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(int phone_num) {
        while(true){
            if ((phone_num>=770000000&&phone_num<=789999999 ) || (phone_num>=700000000&&phone_num<=719999999) || (phone_num>=730000000&&phone_num<=739999999)){
                this.phone_num = phone_num;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                    phone_num=input.nextInt();
                    setPhone_num(phone_num);
            }
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
         while(true){
            if ("male".equals(gender) || "female".equals(gender)){
                this.gender = gender;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                gender=input.nextLine();
                setGender(gender);
            }
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        while(true){
            if (address.matches("[a-zA-Z0-9\\s]+")){
                this.address = address;
                break;
            }
            
            else{
                System.out.println("Invalid synatx");
                System.out.println("Enter again: ");
                address=input.nextLine();
                setAddress(address);
            }
        }
    }
    
    
 
}


