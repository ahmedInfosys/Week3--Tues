import java.text.NumberFormat;
import java.util.*;


public class Evil_Corp {
	
	private int account_number;
	private String name;
	private int balance;
	private static HashMap< String, GregorianCalendar> list = new HashMap< String, GregorianCalendar>();
	private sorting sort = new sorting();
	
	private NumberFormat currency;//for formatting is USD
	
	public void Evil_Corp(){
		account_number = 0;
		name = "";
		balance = 0;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void  store_info(int balance, int transaction, GregorianCalendar date_each_transaction,String sign){
		currency = NumberFormat.getCurrencyInstance();
		String str = " ";
		str = String.format("%-20s%-15s",sign + currency.format(transaction),currency.format(balance));
				
		list.put( str, date_each_transaction);
		
		
		}
	public HashMap< String, GregorianCalendar> get_info(){
		return list;
	}
	public void store_sorted_list(HashMap< String, GregorianCalendar> unsorted_list){
		list = sort.sortHashMapByValuesD(unsorted_list);
	}

}