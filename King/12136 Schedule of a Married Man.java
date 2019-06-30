import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int strToTime (String s) {
		String [] split=s.split(":");
		return Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int wifeStart=strToTime(st.nextToken());
			int wifeEnd=strToTime(st.nextToken());
			if (wifeEnd<wifeStart) wifeEnd+=24*60;
			
			st=new StringTokenizer(br.readLine());
			int meetingStart=strToTime(st.nextToken());
			int meetingEnd=strToTime(st.nextToken());
			if (meetingEnd<meetingStart) meetingEnd+=24*60;
			
			boolean flag=(meetingStart>wifeEnd || meetingEnd<wifeStart);
			System.out.printf("Case %d: %s\n", testCase, flag ? "Hits Meeting" : "Mrs Meeting");
		}
	}

}