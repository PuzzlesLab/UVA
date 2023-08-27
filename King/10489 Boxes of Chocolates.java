import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());

			int curr=0;
			for (int b=0;b<B;b++) {
				st=new StringTokenizer(br.readLine());
				int K=Integer.parseInt(st.nextToken());
				int m=1;
				for (int k=0;k<K;k++) {
					m*=Integer.parseInt(st.nextToken());
					m%=N;
				}
				curr=(curr+m)%N;
			}
			System.out.println(curr);
		}
	}

}