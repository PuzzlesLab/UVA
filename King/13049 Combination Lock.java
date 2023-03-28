import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			st.nextToken(); // Length.
			String iStr=st.nextToken();
			String fStr=st.nextToken();
			
			int [] iNums=new int [iStr.length()];
			for (int i=0;i<iNums.length;i++) iNums[i]=iStr.charAt(i)-'0';
			int [] fNums=new int [fStr.length()];
			for (int i=0;i<fNums.length;i++) fNums[i]=fStr.charAt(i)-'0';
			
			int ans=0;
			for (int i=0;i<iNums.length;i++) {
				int min=Math.min(iNums[i],fNums[i]);
				int max=Math.max(iNums[i],fNums[i]);
				int d1=max-min;
				int d2=min+10-max;
				ans+=Math.min(d1,d2);
			}
			System.out.printf("Case %d: %d\n",t,ans);
		}
	}
}
