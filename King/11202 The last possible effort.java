import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		int [] sqAns=new int [15001];
		sqAns[1]=1;
		sqAns[2]=1;
		int temp=2;
		for (int n=3;n<sqAns.length;n++) {
			sqAns[n]=sqAns[n-1];
			if (n%2==1) {
				sqAns[n]+=temp;
				temp++;
			}
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int ans=0;
			if (N!=M) {
				if ((N&1)==1) N++;
				if ((M&1)==1) M++;
				
				N>>=1;
				M>>=1;
				ans=N*M;
			} else {
				ans=sqAns[N];
			}
			
			System.out.println(ans);
		}
	}
}
