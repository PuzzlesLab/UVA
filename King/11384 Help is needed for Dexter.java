import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	public static void main(String[] args) throws Exception {
		HashSet<Integer> pow2=new HashSet<>();
		for (int i=0;i<31;i++) pow2.add(1<<i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			// Given 2^L > N, Llog2 > log N, find L.
			int L=(int)Math.ceil(Math.log(N)/Math.log(2));
			if (pow2.contains(N)) L++;
			System.out.println(L);
		}
	}

}
