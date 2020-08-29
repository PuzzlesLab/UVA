import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int S=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			StringBuilder sb=new StringBuilder();
			int width=(N+1)*T+S*N;
			for (int n=0;n<N;n++) {
				for (int t=0;t<T;t++) {
					for (int w=0;w<width;w++) sb.append('*');
					sb.append('\n');
				}
				for (int squareRow=0;squareRow<S;squareRow++) {
					for (int w=0;w<width;w++) sb.append((w%(T+S)<T) ? '*' : '.');
					sb.append('\n');
				}
			}
			for (int t=0;t<T;t++) {
				for (int w=0;w<width;w++) sb.append('*');
				sb.append('\n');
			}
			
			System.out.printf("Case %d:\n%s\n",testCase++,sb.toString());
		}

	}
}