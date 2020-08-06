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
			int M=Integer.parseInt(st.nextToken());
			
			int [] budgets=new int [M];
			int [] daedalus=new int [M];
			int [] others=new int [M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				budgets[m]=Integer.parseInt(st.nextToken());
				daedalus[m]=Integer.parseInt(st.nextToken());
				for (int n=1;n<N;n++) others[m]+=Integer.parseInt(st.nextToken());
			}
			
			int daedalusWins1=0;
			for (int m=0;m<M;m++) if (daedalus[m]+others[m]<=budgets[m]) daedalusWins1+=daedalus[m];
			
			int daedalusWins2=0;
			int [] bets= {10000, 1000, 100, 10, 1};
			for (int m=0;m<M;m++) for (int bet : bets) if (bet+others[m]<=budgets[m]) {
				daedalusWins2+=bet;
				break;
			}

			System.out.println(daedalusWins2-daedalusWins1);
		}

	}

}