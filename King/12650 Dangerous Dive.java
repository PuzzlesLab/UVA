import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			
			boolean [] returned=new boolean [N+1];
			st=new StringTokenizer(br.readLine());
			for (int r=0;r<R;r++) returned[Integer.parseInt(st.nextToken())]=true;
			
			if (N!=R) {
				StringBuilder sb=new StringBuilder();
				for (int n=1;n<=N;n++) if (!returned[n]) {
					sb.append(n);
					sb.append(' ');
				}
				System.out.println(sb.toString());
			} else System.out.println('*');
		}
	}
}