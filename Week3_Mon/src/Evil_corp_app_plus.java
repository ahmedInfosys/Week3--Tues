import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;



public class Evil_corp_app_plus {
	
	private static Validator val;
	
	//Method to calculate the checks of the customer's account. It takes object Evil Corp and Return modified Evil Corp.
	public static Evil_Corp check(Evil_Corp this_list){
		
		val = new Validator(); 
		Scanner key = new Scanner(System.in);
		int check ;
		
		check = val.getInt(key, "Enter the amount of the check: ");
		int balance;
		balance = this_list.getBalance() - check;
		this_list.setBalance(balance);//Setting balance into object of Evil Corp
		
		
		//if the balance becomes negative, it starts charging $35 fees each transaction.
		if(balance < 0){
			this_list.setBalance(balance - 35);
			balance = this_list.getBalance();
		}
		
		
		String entered_date = val.getString(key, "Enter the date of the check: ", 10);
		set_date d = new set_date();
		//Converting date into integers of Year, Month, Day and set them into the Gregorian Calendar
		d .setDate(entered_date);
		
		GregorianCalendar date_of_transaction = new GregorianCalendar(d.getYear(),d.getMonth(), d.getDay());
		
		this_list.store_info(balance,check,date_of_transaction, " - ");// '-' sign for checks
		this_list.store_sorted_list( this_list.get_info());
		 
		return this_list;
	}
	
	
	//Method to calculate the deposits of the customer's account. It takes object Evil Corp and Return modified Evil Corp.
	public static Evil_Corp deposite(Evil_Corp this_list){
		
		val = new Validator();
		Scanner key = new Scanner(System.in);
		
		
		int deposite = val.getInt(key, "Enter the amount of the deposite: ");
		
		int balance;
		balance = this_list.getBalance() + deposite;
		this_list.setBalance(balance);

		String entered_date = val.getString(key, "Enter the date of the check: ", 10);
		set_date d = new set_date();
		//Converting date into integers of Year, Month, Day and set them into the Gregorian Calendar
		d .setDate(entered_date);
		
		GregorianCalendar date_of_transaction = new GregorianCalendar(d.getYear(),d.getMonth(), d.getDay());
		
		 this_list.store_info(balance,deposite,date_of_transaction, " + ");
		 this_list.store_sorted_list( this_list.get_info());
	
		return this_list;
	}
	
	
	public static void main(String [] args) throws IOException{
		
		ArrayList<Evil_Corp> list_of_accounts = new ArrayList<Evil_Corp>();//ArrayList of object Evil Corp.
		Evil_Corp evil = new Evil_Corp();//single object of Evil corp class
		Scanner key = new Scanner(System.in);//for reading user's inputs.
		NumberFormat currency = NumberFormat.getCurrencyInstance();//for formatting balance is USD.
		val = new Validator();//For validating users inputs
		SimpleDateFormat sdf = new SimpleDateFormat(" MM/dd/yyyy ");//for formatting dates
		set_date d = new set_date ();// for converting entered date into values for Year, Month, and day.
		
		
		PrintWriter writer = null;
		
		//Information entered by the user.
		int account_number = 0, balance, check;//account number, remaining balance, and payment each transaction
		String name;
		String entered_date = "";
		String filename = (System.getProperty("user.dir") + File.separatorChar + "my_bank_info.txt");
	

		System.out.println("Welcome to Evil Corp Savings and Loan\nPlease create the user account(s)");
		
		Loop1:
		while(true){
			
			
			account_number = val.getInt(key, "Enter an account # or -1 to stop entering accounts");
			if(account_number == -1){break Loop1;}
			evil.setAccount_number(account_number);
			
			
			name = val.getString(key, "Enter the name for acct # " + account_number + " : ", 30);
			evil.setName(name);
			
			
			balance = val.getInt(key, "Enter the balance for acct # " + account_number + " : ");
			evil.setBalance(balance);
			
			
			list_of_accounts.add(evil);
			
		}
		
		account_number = 0;
		String transaction_type = " ";
		int index = 0;

		Loop2:
		while(true){
			
			index = 0;
			account_number = val.getInt(key, "Enter the account # or -1 : ");

			while(list_of_accounts.get(index).getAccount_number() != account_number  ){
				if(index >= list_of_accounts.size()){
					System.out.println("The entered account number is not available in our data base. ");
					break Loop2;
				}
				else if(account_number == -1){
					break Loop2;
				}
				index ++;
			}
			
			//Delete the customer's account only if their balance is zero.
			if(list_of_accounts.get(index).getBalance() == 0){
				list_of_accounts.remove(index);
				break Loop2;
			}
			
			System.out.printf("%-20s%-15s%-15s\n", "     Name    ", "Account number", "Balance");
			System.out.printf("%-20s%-15s%-15s\n", "------------------", "--------------", "-----------");
			System.out.printf("%-20s%-15s%-15s\n", list_of_accounts.get(index).getName(),list_of_accounts.get(index).getAccount_number(),currency.format(list_of_accounts.get(index).getBalance()));

			
			transaction_type = val.getString(key, "Enter a transaction type (Check, Debit card, Deposit or Withdrawal) or -1 to finish :", 1);
	
					
			switch (transaction_type){
			
			case "c"://Letter 'c' for checking.
				
				evil = list_of_accounts.get(index);
				list_of_accounts.remove(index);
				list_of_accounts.add(index, check(evil));
				
				break;
			case "d"://Letter 'd' for deposit.
				evil = list_of_accounts.get(index);
				list_of_accounts.remove(index);
				list_of_accounts.add(index, deposite(evil));
				break;
			case "-1" :
				writer = new PrintWriter (new File(filename));
				writer.printf("%-20s%-25s%-15s\n", "Transaction date", "Check/Deposite", "Balance");
				writer.println();
				writer.printf("%-20s%-25s%-15s\n", "------------------", "---------------", "---------");
				writer.println();
				for (String KEY:list_of_accounts.get(index).get_info().keySet()){
					writer.println(sdf.format(list_of_accounts.get(index).get_info().get(KEY).getTime())  + "\t\t" + KEY);
				}
				writer.close();

				break Loop2;
			default: System.out.println("Invalid option. Try again.");
			}
			
			}
		
		
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = reader.readLine();
		while(line != null){
			
			System.out.println(line);
			line = reader.readLine();
			
		}
		
		reader.close();

	}
}


		
		

