import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int calcBest(int x) {
		int result=1;
		while (x>0) {
			result+=x%2;
			x>>=1;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int M=Integer.parseInt(br.readLine());
		for (int m=0;m<M;m++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int X=Integer.parseInt(st.nextToken());
			int best=calcBest(X);
			int worst=(1<<N)-((X==0)?(1<<N):(X&-X))+1;
			System.out.printf("%d %d\n",best,worst);
		}
	}
}
