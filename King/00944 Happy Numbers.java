import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_N=100000;
	private static int [] DpNext=new int [MAX_N];

	private static int getNext(int n) {
		if (DpNext[n]==0) {
			int sum=0;
			while (n>0) {
				int digit=n%10;
				sum+=digit*digit;
				n/=10;
			}
			DpNext[n]=sum;
		}
		return DpNext[n];
	}

	public static void main(String[] args) throws Exception {
		int [] dp=new int [MAX_N];
		for (int i=1;i<MAX_N;i++) {
			int temp=i;
			int length=1;

			boolean [] exists=new boolean [MAX_N];
			while (temp!=1) {
				temp=getNext(temp);
				if (exists[temp]) break;
				if (dp[temp]!=0) {
					length+=dp[temp];
					temp=1;
					break;
				}
				exists[temp]=true;
				length++;
			}

			if (temp==1) {
				dp[i]=length;
			}
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int L=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());

			StringBuilder sb=new StringBuilder();
			for (int i=L;i<=H;i++) {
				if (dp[i]>0) {
					sb.append(i);
					sb.append(' ');
					sb.append(dp[i]);
					sb.append('\n');
				}
			}
			
			if (first) first=false;
			else System.out.println();
			System.out.print(sb.toString());
		}
	}

}
