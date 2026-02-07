import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	public static void main(String [] args) throws Exception {
		final long MAX=50000000;
		long n=0;
		HashMap<Long,Integer> ansMap=new HashMap<>();
		while (true) {
			long count=((n+1)*(n+2))>>1; // Star & bar problem
			if (count>MAX) break;
			ansMap.put(count,(int)n);
			n++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			long S=Integer.parseInt(br.readLine());
			System.out.println(ansMap.containsKey(S) ? ansMap.get(S) : "No solution");
		}
	}

}
