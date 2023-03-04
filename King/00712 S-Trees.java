import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] order=new int [N];
			for (int n=0;n<N;n++) order[n]=st.nextToken().charAt(1)-'0'-1;

			String tree=br.readLine();
			int M=Integer.parseInt(br.readLine());

			StringBuilder sb=new StringBuilder();
			sb.append("S-Tree #");
			sb.append(testCase++);
			sb.append(":\n");
			for (int m=0;m<M;m++) {
				s=br.readLine();
				int [] vva=new int [N];
				for (int n=0;n<N;n++) vva[n]=s.charAt(n)-'0';

				int node=0;
				for (int n=0;n<N;n++) node=(node<<1)+vva[order[n]];
				sb.append(tree.charAt(node));
			}
			sb.append('\n');
			System.out.println(sb.toString());
		}
	}

}