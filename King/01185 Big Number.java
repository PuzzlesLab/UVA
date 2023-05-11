import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			double digits=0;
			for (int n=2;n<=N;n++) digits+=Math.log10(n);
			int ans=(int)Math.floor(digits)+1;
			System.out.println(ans);
		}
	}

}
