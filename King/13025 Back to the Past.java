import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Main {
	
	public static void main (String [] args) throws Exception {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("MMM dd, YYY EEEE");
		LocalDate ld=LocalDate.of(2013, 5, 29);
		System.out.println(ld.format(dtf));
	}

}