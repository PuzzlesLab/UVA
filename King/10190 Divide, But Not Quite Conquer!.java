import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			long l=1;
			for (int p=1;l<N && p<=31;p++) l*=M;

			StringBuilder sb=new StringBuilder();
			if (l!=N || N<=1 || M<=1) sb.append("Boring! ");
			else {
				while (N>0) {
					sb.append(N);
					sb.append(' ');
					N/=M;
				}
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
