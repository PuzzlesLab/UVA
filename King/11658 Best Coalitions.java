import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken())-1;
			
			int [] share=new int [N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine(), ".");
				share[n]=Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken());
			}
			
			double ans=100.0;
			int startShare=share[X];
			if (startShare<=5000) {
				boolean [] dp=new boolean [10001];
				dp[0]=true;

				for (int n=0;n<share.length;n++) if (n!=X) {
					for (int i=dp.length-1-share[n];i>=0;i--) {
						if (dp[i]) dp[i+share[n]]=true;
					}
				}
				
				int rem=0;
				for (int i=5001-startShare;i<dp.length;i++) if (dp[i]) {
					rem=i;
					break;
				}
				ans=(startShare*100.0)/(startShare+rem);
			}
			System.out.printf("%.2f\n",ans);
		}
	}

}
