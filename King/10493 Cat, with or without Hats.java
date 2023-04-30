import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			String ans="";
			if (N==1) ans=(M==1 ? "Multiple" : "Impossible");
			else {
				// T=(T-M)(N)+1, find T.
				int divisor=N-1;
				int dividen=(N*M)-1; 
				ans=dividen%divisor==0 ? String.valueOf(dividen/divisor) : "Impossible";
			}
			System.out.printf("%d %d %s\n",N,M,ans);
		}
	}
}
