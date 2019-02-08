import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		boolean [] notPrime=new boolean [1000001];
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;

		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			StringBuilder sb=new StringBuilder();
			sb.append(N);
			sb.append(":\n");
			boolean found=false;
			for (int i=2;i<=N/2 && !found;i++) if (!notPrime[i] && !notPrime[N-i]) {
				sb.append(i);
				sb.append('+');
				sb.append(N-i);
				found=true;
			}
			
			if (!found) sb.append("NO WAY!");
			System.out.println(sb.toString());
		}
	}

}