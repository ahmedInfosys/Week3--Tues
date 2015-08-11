//This class converts date string into integers for Year, Month, and day of week.

public class set_date {
	private int Day;
	private int Month;
	private int Year;
	
	
	public int getDay() {
		return Day;
	}
	
	public int getMonth() {
		return Month;
	}
	
	public int getYear() {
		return Year;
	}
	public void setDate(String DATE) {
		Year = Integer.parseInt(DATE.substring(6, 10));
		Month = Integer.parseInt(DATE.substring(0, 2)) - 1;
		Day = Integer.parseInt(DATE.substring(3, 5));
		
	
	}
	
	
}
