import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int a1=Integer.parseInt(st.nextToken());
        	int a2=Integer.parseInt(st.nextToken());
        	if (a1>=a2) break;
        	
        	long [] dp=new long [a2+1];
        	dp[a1]=1;
        	for (int b=a1;b<=a2;b++) {
        		if (dp[b]==0) continue;
        		for (int i2=b+1;i2<=a2;i2++) if (b%(i2-b)==0) dp[i2]+=dp[b];
        	}

        	System.out.printf("%d %d %d\n",a1,a2,dp[a2]);
        }
	}

}