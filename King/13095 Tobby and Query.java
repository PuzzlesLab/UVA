import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine()).length()>0) {
			// End of input is a new line, what a shame that this is not stated in the question!
			// Using scanner will lead to TLE.
			int N=Integer.parseInt(s);

			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] nums=new int [N];
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			
			int [][] appears=new int [11][N+1];
			for (int n=1;n<=N;n++) {
				int num=nums[n-1];
				for (int i=0;i<appears.length;i++) appears[i][n]=appears[i][n-1];
				appears[num][n]++;
			}
			
			StringBuilder sb=new StringBuilder();
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int L=Integer.parseInt(st.nextToken());
				int R=Integer.parseInt(st.nextToken());

				int ans=0;
				for (int i=0;i<appears.length;i++) if(appears[i][R]-appears[i][L-1]>0) ans++;
				sb.append(ans);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}
