import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			br.readLine();
			long K=Long.parseLong(br.readLine());
			if (K<0) K=-K;
			long curr=0;
			
			int ans=0;
			for (int i=1;;i++) {
				curr+=i;
				if (curr>=K && (curr-K)%2==0) {
					ans=i;
					break;
				}
			}
			if (n>0) System.out.println();
			System.out.println(ans);
		}
	}

}
