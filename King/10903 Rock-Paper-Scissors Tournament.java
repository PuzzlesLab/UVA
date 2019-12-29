import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		String ROCK="rock", PAPER="paper", SCISSORS="scissors";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=0;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			double [] win=new double [N];
			double [] lose=new double [N];
			
			int max=(K*N*(N-1))/2;
			for (int k=0;k<max;k++) {
				st=new StringTokenizer(br.readLine());
				int p1=Integer.parseInt(st.nextToken())-1;
				String m1=st.nextToken();
				int p2=Integer.parseInt(st.nextToken())-1;
				String m2=st.nextToken();
				
				if (!m1.equals(m2)) {
					String temp=m1+m2;
					if (temp.equals(ROCK+SCISSORS) || temp.equals(PAPER+ROCK) || temp.equals(SCISSORS+PAPER)) {
						win[p1]++;
						lose[p2]++;
					} else {
						lose[p1]++;
						win[p2]++;
					}
				}
			}
			
			if (T>0) System.out.println();
			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n++) {
				if (win[n]+lose[n]>0) sb.append(String.format("%.3f\n", win[n]/(win[n]+lose[n])));
				else sb.append("-\n");
			}
			System.out.print(sb.toString());
			T++;
		}
	}

}