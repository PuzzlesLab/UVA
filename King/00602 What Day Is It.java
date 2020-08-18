import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

class Main {
	
	private static int [] DayOfMonthsMaxNormal= {0,31,28,31,30,31,30,31,31,30,31,30,31};
	private static int [] DayOfMonthsMaxLeap= {0,31,29,31,30,31,30,31,31,30,31,30,31};
	private static String [] MonthName={"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	private static String invalid(int day, int month, int year) {
		return String.format("%d/%d/%d is an invalid date.", month, day, year);
	}
	
	private static int getDateType(int day, int month, int year) {
		if (year>1752) return 2;
		else if (year<1752) return 1;
		else if (month<9) return 1;
		else if (month>9) return 2;
		else if (day<=2) return 1;
		else if (day>=14) return 2;
		return -1;
	}
	
	private static String [] JulianDayName= {"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };//1 1 0 is friday!
	private static String julian(int day, int month, int year) {
		if (year<0 || month<=0 || month>=13 || day<=0) return invalid(day,month,year);
		if ((year%4==0 && day>DayOfMonthsMaxLeap[month]) ||
			(year%4!=0 && day>DayOfMonthsMaxNormal[month])) return invalid(day,month,year);
			
		int tempYear=0, tempMonth=1, tempDay=1, dayElapsed=0;
		while (tempYear<year) {
			dayElapsed+=(tempYear%4!=0 || tempYear==0) ? 365: 366;
			dayElapsed%=7;
			tempYear++;
		}
		while (tempMonth<month) {
			dayElapsed+=(tempYear%4!=0 || tempYear==0) ? DayOfMonthsMaxNormal[tempMonth]: DayOfMonthsMaxLeap[tempMonth];
			dayElapsed%=7;
			tempMonth++;
		}
		dayElapsed+=(day-tempDay);
		dayElapsed%=7;
		
		StringBuilder sb=new StringBuilder();
		sb.append(MonthName[month]);
		sb.append(' ');
		sb.append(day);
		sb.append(", ");
		sb.append(year);
		sb.append(" is a ");
		sb.append(JulianDayName[dayElapsed]);
		return sb.toString();
	}
	
	private static DateTimeFormatter df2=DateTimeFormatter.ofPattern("EEEE");
	
	private static String gregorian(int day, int month, int year) {
		try {
			LocalDateTime ld=LocalDateTime.of(year, month, day, 0, 0);
			return String.format("%s %d, %d", MonthName[month],day,year)+" is a "+ld.format(df2);
		} catch (Exception e) {
			return invalid(day, month, year);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int month=Integer.parseInt(st.nextToken());
			int day=Integer.parseInt(st.nextToken());
			int year=Integer.parseInt(st.nextToken());
			
			int type=getDateType(day,month,year);
			if (type==-1) System.out.println(invalid(day,month,year));
			else if (type==1) System.out.println(julian(day,month,year));
			else if (type==2) System.out.println(gregorian(day,month,year));
		}
	}

}