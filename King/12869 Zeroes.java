import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long low=Long.parseLong(st.nextToken());
			long high=Long.parseLong(st.nextToken());
			if (low==0 && high==0) break;

			// Every zero is contributed by a prime factor of 2 & a prime factor of 5
			// Every 5 numbers will increase prime factor 5 by 1 (and 1 more trailing zero)
			low/=5;
			high/=5;

			// Print on every test case will cause TLE :/
			sb.append(high-low+1);
			sb.append('\n');
		}
		System.out.print(sb);
	}

}
