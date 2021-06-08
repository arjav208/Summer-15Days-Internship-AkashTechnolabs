
import java.util.Scanner;


public class Get_user_imput {
	public static void main(String args[]){  

		Get_user_imput getuser = new Get_user_imput();
		getuser.con();
		
        }
	
	
	public void con(){
		
		 Scanner myObj = new Scanner(System.in);

		    System.out.println("Enter name, city and age");

		    // String input
		    
		    
		    String name = myObj.nextLine();
		    
		    String city = myObj.nextLine();

		    // Numerical input
		    
		    int age = myObj.nextInt();
		   
		    // Output input by user
		    System.out.println("Name: " + name); 
		    System.out.println("Age: " + age); 
		    System.out.println("city: " + city);    
		    
//		    con();
	}
	
	
}
