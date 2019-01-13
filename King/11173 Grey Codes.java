import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			
			StringBuilder sb=new StringBuilder();
			while (k>0) {
				int temp=k%4;
				if (temp==0 || temp==3) sb.append(0);
				else sb.append(1);
				k/=2;
			}
			sb.reverse();
			if (sb.length()==0) sb.append(0);
			
			System.out.println(new BigInteger(sb.toString(), 2).toString()); //For some reason, this is faster than Integer.parseInt()!
		}
	}

}
