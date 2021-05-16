import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int [] weights=new int [10];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<weights.length;i++) weights[i]=Integer.parseInt(st.nextToken());
			
			int [] value=new int [5];
			int [] solution=null;
			for (int a=1;a<=weights[0]/2 && solution==null;a++) {
				value[0]=a;
				value[1]=weights[0]-value[0];
				value[2]=weights[1]-value[0];
				if (value[0]<=value[1] && value[1]<=value[2]) {
					for (int d=value[2];d<=weights[weights.length-1]/2 && solution==null;d++) {
						value[3]=d;
						value[4]=weights[weights.length-1]-value[3];
						if (value[3]<=value[4]) {
							int [] checkWeight=new int [10];
							int checkWeightCount=0;
							for (int i=0;i<value.length;i++) for (int i2=i+1;i2<value.length;i2++) checkWeight[checkWeightCount++]=value[i]+value[i2];
							Arrays.sort(checkWeight);
							
							boolean flag=true;
							for (int i=0;i<weights.length;i++) flag &= weights[i]==checkWeight[i];
							if (flag) {
								solution=new int [value.length];
								for (int i=0;i<value.length;i++) solution[i]=value[i];
							}
						}
					}
				} else break;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(':');
			for (int i=0;i<solution.length;i++) {
				sb.append(' ');
				sb.append(solution[i]);
			}
			System.out.println(sb.toString());
		}
	}
}