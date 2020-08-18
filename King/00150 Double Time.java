import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static int [] DaysInMonthNormal= {0,31,28,31,30,31,30,31,31,30,31,30,31};
	private static int [] DaysInMonthLeap= {0,31,29,31,30,31,30,31,31,30,31,30,31};
	
	private static boolean gregorianLeap(int year) {
		if (year==0) return false;
		else if (year%4!=0) return false;
		else if (year%100!=0) return true;
		else if (year%400!=0) return false;
		return true;
	}
	
	private static boolean julianLeap(int year) {
		return (year>=8 && year%4==0);
	}
	
	private static int calcDaysElapsedGregorian(int day, int month, int year) {
		int tempMonth=1, tempYear=1, daysElapsed=0;
		while (tempYear<year) {
			daysElapsed+=(gregorianLeap(tempYear)) ? 366 : 365;
			tempYear++;
		}
		while (tempMonth<month) {
			daysElapsed+=(gregorianLeap(tempYear)) ? DaysInMonthLeap[tempMonth] : DaysInMonthNormal[tempMonth];
			tempMonth++;
		}
		daysElapsed+=day;
		return daysElapsed;
	}
	
	private static int calcDaysElapsedJulian(int day, int month, int year) {
		int tempMonth=1, tempYear=1, daysElapsed=0;
		while (tempYear<year) {
			daysElapsed+=(julianLeap(tempYear)) ? 366 : 365;
			tempYear++;
		}
		while (tempMonth<month) {
			daysElapsed+=(julianLeap(tempYear)) ? DaysInMonthLeap[tempMonth] : DaysInMonthNormal[tempMonth];
			tempMonth++;
		}
		daysElapsed+=(day-2);
		return daysElapsed;
	}
	

	private static String[] MonthString= {"","January","February","March","April","May","June","July","August","September","October","November","December"};
	private static String[] DayString= {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	private static HashMap<String, Integer> MonthMap=new HashMap<>();
	private static HashMap<String, Integer> DayMap=new HashMap<>();
	//1 Jan 0: Friday
	private static String daysElapsedToGregorian(int daysElapsed) {
		int daysElapsedBackup=daysElapsed;
		int day=1, month=1, year=1;
		while (true) {
			int daysInYear=gregorianLeap(year) ? 366 : 365;
			if (daysElapsed>=daysInYear) {
				daysElapsed-=daysInYear;
				year++;
			} else break;
		}
		while (true) {
			int daysInMonth=gregorianLeap(year) ? DaysInMonthLeap[month] : DaysInMonthNormal[month];
			if (daysElapsed>=daysInMonth) {
				daysElapsed-=daysInMonth;
				month++;
			} else break;
		}
		day+=daysElapsed;
		String dayOfWeekString=DayString[(daysElapsedBackup+1)%7];
		String monthString=MonthString[month];
		return String.format("%s %d %s %d",dayOfWeekString,day,monthString,year);
	}
	
	private static String daysElapsedToJulian(int daysElapsed) {
		int daysElapsedBackup=daysElapsed;
		int day=1, month=1, year=1;
		while (true) {
			int daysInYear=julianLeap(year) ? 366 : 365;
			if (daysElapsed>=daysInYear) {
				daysElapsed-=daysInYear;
				year++;
			} else break;
		}
		while (true) {
			int daysInMonth=julianLeap(year) ? DaysInMonthLeap[month] : DaysInMonthNormal[month];
			if (daysElapsed>=daysInMonth) {
				daysElapsed-=daysInMonth;
				month++;
			} else break;
		}
		day+=daysElapsed;
		String dayOfWeekString=DayString[daysElapsedBackup%7];
		String monthString=MonthString[month];
		return String.format("%s %d* %s %d",dayOfWeekString,day,monthString,year);
	}
	
	public static void main (String [] args) throws Exception {
		for (int i=1;i<MonthString.length;i++) MonthMap.put(MonthString[i],i);
		for (int i=0;i<DayString.length;i++) DayMap.put(DayString[i],i);
		
		DateTimeFormatter df=DateTimeFormatter.ofPattern("EEEE");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			String dayOfWeek=st.nextToken();
			int dayOfMonth=Integer.parseInt(st.nextToken());
			int month=MonthMap.get(st.nextToken());
			int year=Integer.parseInt(st.nextToken());
			
			LocalDateTime gregorian=LocalDateTime.of(year,month,dayOfMonth,1,1);
			String gregorianDayOfWeek=gregorian.format(df);
			
			if (dayOfWeek.equals(gregorianDayOfWeek)) {
				int daysElapsed=calcDaysElapsedGregorian(dayOfMonth,month,year);
				System.out.println(daysElapsedToJulian(daysElapsed));
			} else {
				int daysElapsed=calcDaysElapsedJulian(dayOfMonth,month,year);
				System.out.println(daysElapsedToGregorian(daysElapsed));
			}
		}
	}

}