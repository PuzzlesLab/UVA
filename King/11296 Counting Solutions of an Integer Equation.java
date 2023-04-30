import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			long ans=0;
			for (int x=0;x<=N;x++) {
				int rem=N-x;
				// 2(y+z)= rem
				if (rem%2==1) continue; // Odd rem is not possible.
				// y+z = rem/2, combination of y+z = (rem/2)+1
				ans+=(rem>>1)+1;
			}
			System.out.println(ans);
		}
	}
}
