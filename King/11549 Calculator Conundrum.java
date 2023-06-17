import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int MAX_N=1;
			for (int n=0;n<N;n++) MAX_N*=10;
			
			HashSet<Long> visited=new HashSet<>();
			long ans=K;
			long temp=K;
			while (true) {
				temp*=temp;
				while (temp>=MAX_N) temp/=10;
				if (visited.contains(temp)) break;
				ans=Math.max(ans,temp);
				visited.add(temp);
			}
			System.out.println(ans);
		}
	}

}