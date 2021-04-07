import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [] serveBy=new int [N+1];
			int [] dist=new int [N+1];
			
			StringBuilder sb=new StringBuilder();
			while (true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char op=st.nextToken().charAt(0);
				if (op=='O') break;

				int I=Integer.parseInt(st.nextToken());
				if (op=='E') {
					int sum=0, temp=I;
					while (serveBy[temp]!=0) {
						sum+=dist[temp];
						temp=serveBy[temp];
					}
					if (I!=temp) {
						serveBy[I]=temp;
						dist[I]=sum;
					}
					sb.append(sum);
					sb.append('\n');
				} else if (op=='I') {
					int J=Integer.parseInt(st.nextToken());
					serveBy[I]=J;
					dist[I]=Math.abs(I-J)%1000;
				}
			}
			System.out.print(sb.toString());
		}
	}
}
