import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {

		HashMap<String, Integer> minuteDelta=new HashMap<>();
		minuteDelta.put("UTC", 0);
		minuteDelta.put("GMT", 0);
		minuteDelta.put("BST", 60);
		minuteDelta.put("IST", 60);
		minuteDelta.put("WET", 0);
		minuteDelta.put("WEST", 60);
		minuteDelta.put("CET", 60);
		minuteDelta.put("CEST", 120);
		minuteDelta.put("EET", 120);
		minuteDelta.put("EEST", 180);
		minuteDelta.put("MSK", 180);
		minuteDelta.put("MSD", 240);
		minuteDelta.put("AST", -240);
		minuteDelta.put("ADT", -180);
		minuteDelta.put("NST", -210);
		minuteDelta.put("NDT", -150);
		minuteDelta.put("EST", -300);
		minuteDelta.put("EDT", -240);
		minuteDelta.put("CST", -360);
		minuteDelta.put("CDT", -300);
		minuteDelta.put("MST", -420);
		minuteDelta.put("MDT", -360);
		minuteDelta.put("PST", -480);
		minuteDelta.put("PDT", -420);
		minuteDelta.put("HST", -600);
		minuteDelta.put("AKST", -540);
		minuteDelta.put("AKDT", -480);
		minuteDelta.put("AEST", 600);
		minuteDelta.put("AEDT", 660);
		minuteDelta.put("ACST", 570);
		minuteDelta.put("ACDT", 630);
		minuteDelta.put("AWST", 480);
		
		DateTimeFormatter df=DateTimeFormatter.ofPattern("h:mm a");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			LocalDateTime dt=LocalDateTime.of(LocalDate.now(), LocalTime.now()).withSecond(0).withNano(0);
			StringTokenizer st=new StringTokenizer(br.readLine());
			if (st.countTokens() == 4) {
				String [] s=st.nextToken().split(":");
				String ampm=st.nextToken();
				int H=Integer.parseInt(s[0]), M=Integer.parseInt(s[1]);
				if (ampm.equals("a.m.") && H==12) H=0;
				else if (ampm.equals("p.m.") && H<12) H+=12;
				dt=dt.withHour(H).withMinute(M);
			} else {
				String s=st.nextToken();
				if (s.equals("midnight")) dt=dt.withHour(0).withMinute(0);
				else if (s.equals("noon")) dt=dt.withHour(12).withMinute(0);
			}
			
			String tz1=st.nextToken(), tz2=st.nextToken();
			int mDelta1=minuteDelta.get(tz1), mDelta2=minuteDelta.get(tz2);
			dt=dt.minusMinutes(mDelta1).plusMinutes(mDelta2);
			
			if (dt.getHour()==0 && dt.getMinute()==0) System.out.println("midnight");
			else if (dt.getHour()==12 && dt.getMinute()==0) System.out.println("noon");
			else System.out.println(dt.format(df).toLowerCase().replace("m", ".m."));
		}
		
	}

}
