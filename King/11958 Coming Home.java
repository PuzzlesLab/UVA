import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int K=Integer.parseInt(st.nextToken());
			String [] timeStr=st.nextToken().split(":");
			int currTime=Integer.parseInt(timeStr[0])*60+Integer.parseInt(timeStr[1]);
			
			int fastest=Integer.MAX_VALUE;
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				timeStr=st.nextToken().split(":");
				int time=Integer.parseInt(timeStr[0])*60+Integer.parseInt(timeStr[1]);
				if (time<currTime) time+=24*60;
				time+=Integer.parseInt(st.nextToken());
				
				fastest=Math.min(fastest, time-currTime);
			}
			System.out.printf("Case %d: %d\n", testCase, fastest);
		}
	}

}
