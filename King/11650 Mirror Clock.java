import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine(), ":");
			int hour=Integer.parseInt(st.nextToken());
			int min=60-Integer.parseInt(st.nextToken());
			if (min==60) min=0;
			
			if (hour<12) hour=12-hour;
			if (min>0) {
				hour-=1;
				if (hour==0) hour=12;
			}

			System.out.printf("%02d:%02d\n", hour, min);
		}
	}

}