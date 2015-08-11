//Validator
import java.util.Scanner;  
public class Validator {     
	public static String getString(Scanner sc, String prompt, int range) {
		System.out.println(prompt);         
		String s = "";  
		boolean isValid = false;
		// read the user entry         
		
		while( isValid == false){
			
			s = sc.next();
			sc.nextLine();
			if(s.length() > range){
				System.out.println("The entered string should have at most" + range + " characters, Try again.");
				
				}
			
			else { isValid = true;}     
			     
			}
		return s;
	}
	
	public static int getInt(Scanner sc, String prompt){         
		int i = 0;         
		boolean isValid = false;         
		while (isValid == false){             
			System.out.println(prompt);
			if(sc.hasNextInt()){
				i = sc.nextInt();
				isValid = true;
			}else{                
				System.out.println("Error! Invalid integer value. Try again.");             
				}             
			sc.nextLine();  // discard any other        
			}        
		return i; 

	}
}