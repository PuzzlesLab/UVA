import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int M=Integer.parseInt(br.readLine());
			int S=Integer.parseInt(br.readLine());
			int [] threshold=new int [4];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<threshold.length;i++) threshold[i]=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(br.readLine());
			boolean [] enabled=new boolean [threshold.length];
			for (int i=0;i<4;i++) enabled[i]=((C>>i)&1)!=0;
			boolean [] triggerType=new boolean [threshold.length];
			for (int i=4;i<8;i++) triggerType[i-4]=((C>>i)&1)!=0;

			int sensorCurr=S;
			int currTempStart=0;
			int currTempEnd=0;
			int K=Integer.parseInt(br.readLine());
			boolean [] triggered=new boolean [threshold.length];
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				int duration=Integer.parseInt(st.nextToken());
				int temp=Integer.parseInt(st.nextToken());
				
				currTempEnd=currTempStart+duration;
				while (sensorCurr<currTempStart) sensorCurr+=M;
				if (sensorCurr<=currTempEnd) {
					for (int i=0;i<4;i++) if (enabled[i] && !triggered[i]) {
						triggered[i]=(triggerType[i] && temp>threshold[i]) || (!triggerType[i] && temp<threshold[i]);
					}
				}
				currTempStart=currTempEnd;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(':');
			for (int i=0;i<threshold.length;i++) sb.append(triggered[i] ? " yes" : " no");
			System.out.println(sb.toString());
		}
	}
}