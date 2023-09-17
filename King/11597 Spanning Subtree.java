import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			sb.append(N>>1);
			System.out.println(sb);
		}
	}

}
