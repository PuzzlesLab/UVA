import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			char [] rockType=new char [N];
			int [] rock=new int[N];
			
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				String [] split=st.nextToken().split("-");
				rockType[n]=split[0].charAt(0);
				rock[n]=Integer.parseInt(split[1]);
			}
			
			int ans=Integer.MIN_VALUE;
			int curr=0;
			for (int n=0;n<N;n++) if (rockType[n]=='B' || n%2==0) {
				ans=Math.max(ans,rock[n]-curr);
				curr=rock[n];
			}
			ans=Math.max(ans,D-curr);
			curr=D;
			
			for (int n=N-1;n>=0;n--) if (rockType[n]=='B' || n%2==1) {
				ans=Math.max(ans,curr-rock[n]);
				curr=rock[n];
			}
			ans=Math.max(ans,curr);

			System.out.printf("Case %d: %d\n",testCase,ans);
		}
	}

}