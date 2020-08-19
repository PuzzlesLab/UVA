import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Date implements Comparable<Date> {
		long year;
		int month, day;
		public Date(int y, int m, int d) {
			this.year=y;
			this.month=m;
			this.day=d;
		}
		
		public int compareTo(Date d) {
			if (this.year!=d.year) return this.year>d.year ? 1 : -1;
			else if (this.month!=d.month) return this.month-d.month;
			return this.day-d.day;
		}
	}
	
	private static void generateCombi(int [] ints, boolean [] taken, int [] result, int resultLevel, ArrayList<Date> list) {
		if (resultLevel==3) list.add(new Date(result[0],result[1],result[2]));
		else {
			for (int i=0;i<ints.length;i++) if (!taken[i]) {
				taken[i]=true;
				result[resultLevel]=ints[i];
				generateCombi(ints,taken,result,resultLevel+1,list);
				taken[i]=false;
			}
		}
	}
	
	private static boolean isLeapYear(long y) {
		if (y%4!=0) return false;
		else if (y%100!=0) return true;
		else if (y%400!=0) return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		int [] normalYearMonths= {0,31,28,31,30,31,30,31,31,30,31,30,31};
		int [] leapYearMonths= {0,31,29,31,30,31,30,31,31,30,31,30,31};
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long C=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			int Z=Integer.parseInt(st.nextToken());
			
			int [] ints={X,Y,Z};
			ArrayList<Date> candidates=new ArrayList<>();
			generateCombi(ints,new boolean [3],new int [3], 0, candidates);
			
			for (Date date: candidates) date.year+=C*100;
			Collections.sort(candidates);
			
			String solution="-1";
			for (Date date : candidates) if (date.month>=1 && date.month<=12 && date.day>=1) {
				int monthMaxDay=(isLeapYear(date.year) ? leapYearMonths[date.month] : normalYearMonths[date.month]);
				if (date.day<=monthMaxDay) {
					date.year-=C*100;
					solution=String.format("%02d %02d %02d",date.year,date.month,date.day);
					break;
				}
			}
				
			System.out.println(solution);
		}
	}
}