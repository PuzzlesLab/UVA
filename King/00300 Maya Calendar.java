import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		String [] haabMonths = {"pop", "no", "zip", "zotz", "tzec", 
						  		"xul", "yoxkin", "mol", "chen", "yax",
						  		"zac", "ceh", "mac", "kankin", "muan",
						  		"pax", "koyab", "cumhu", "uayet"};
		HashMap<String, Integer> hmMap=new HashMap<>();
		for (int i=0;i<haabMonths.length;i++) hmMap.put(haabMonths[i],i);
		
		String [] tzolkinDays = {"imix", "ik", "akbal", "kan", "chicchan",
								"cimi", "manik", "lamat", "muluk", "ok", 
								"chuen", "eb", "ben", "ix", "mem", "cib",
								"caban", "eznab", "canac", "ahau" };
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		System.out.println(testCaseCount);
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String dayStr=st.nextToken();
			int day=Integer.parseInt(dayStr.substring(0, dayStr.length()-1));
			String month=st.nextToken();
			int year=Integer.parseInt(st.nextToken());
			
			int totalDays=year*365+hmMap.get(month)*20+day;
			int yearAns=totalDays/260;
			totalDays%=260;
			String dayAns=tzolkinDays[totalDays%20];
			int monthAns=totalDays%13;
			
			System.out.printf("%d %s %d\n", monthAns+1, dayAns, yearAns);
		}
	}

}
