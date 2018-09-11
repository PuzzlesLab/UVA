import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long C=Long.parseLong(st.nextToken()), R=Long.parseLong(st.nextToken());
			long z=C-R;
			TreeSet<Long> factors=new TreeSet<>();
			for (int i=1;i<=Math.sqrt(z);i++) if (z%i==0) {
				if (i>R) factors.add((long)i);
				if (z/i>R) factors.add(z/i);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(n);
			sb.append(":");
			for (long l : factors) {
				sb.append(" ");
				sb.append(l);
			}
			if (R==C) sb.append(" 0");
			System.out.println(sb.toString());
		}
	}

}