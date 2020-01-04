import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

class Main {
	
	public static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
	public static int lcm(int a, int b) { return a * (b / gcd(a, b)); }
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			HashSet<Integer> factors=new HashSet<>();
			for (int i=1;i*i<=N;i++) if (N%i==0) {
				factors.add(i);
				factors.add(N/i);
			}
			ArrayList<Integer> factorList=new ArrayList<>(factors);
			
			int count=0;
			for (int i=0;i<factorList.size();i++) for (int i2=i;i2<factorList.size();i2++) if (lcm(factorList.get(i), factorList.get(i2))==N) count++;
			System.out.println(N+" "+count);
		}
	}

}