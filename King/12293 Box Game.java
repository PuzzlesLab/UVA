import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	public static void main(String[] args) throws Exception {
		// Observation by testing 1-300 with backtracking + DP.
		HashSet<Integer> lose=new HashSet<>();
		final int MAX=200000000;
		int pow=1;
		while (true) {
			pow<<=1;
			lose.add(pow-1);
			if (pow>MAX) break;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			System.out.println((lose.contains(N))?"Bob":"Alice");
		}
	}

}

