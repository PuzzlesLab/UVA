import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int diff=end-start;
			
			int max=0; // n(n+1) to find peak (or second peak)
			for (int i=1;;i++) {
				int sum=i*(i+1);
				if (sum>diff) break;
				else max=i;
			}
			
			int ans=max*2; // steps to peak
			diff-=max*(max+1);
			for (int i=max+1;i>0 && diff>0;i--) { // greedily take from peak+1
				if (diff>=i) {
					ans+=diff/i;
					diff%=i;
				}
			}
			
			System.out.println(ans);
		}
	}

}
