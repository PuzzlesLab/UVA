import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_DP=3001;
	private static int [] DpDigitSum=new int [MAX_DP];
	private static int [] DpNext=new int [MAX_DP];
	private static int [] DpLength=new int [MAX_DP];

	private static int digitSum(int n) {
		if (DpDigitSum[n]==0) {
			int sum=0;
			int temp=n;
			while (temp>0) {
				sum+=temp%10;
				temp/=10;
			}
			DpDigitSum[n]=sum;
		}
		return DpDigitSum[n];
	}

	private static int getNext(int n) {
		if (DpNext[n]==0) {
			int sum=0;
			for (int f=1;f*f<=n;f++) if (n%f==0) {
				int f2=n/f;
				sum+=digitSum(f);
				if (f!=f2) sum+=digitSum(f2);
			}
			DpNext[n]=sum;
		}
		return DpNext[n];
	}
	
	private static int getChainLength(int n) {
		if (DpLength[n]==0) {
			int next=getNext(n);
			DpLength[n]=next==n?1:1+getChainLength(next);
		}
		return DpLength[n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			int minV=Math.min(M, N);
			int maxV=Math.max(M, N);
			int maxLen=0;
			int maxI=-1;
			for (int i=minV;i<=maxV;i++) {
				int len=getChainLength(i);
				if (len>maxLen) {
					maxLen=len;
					maxI=i;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Input");
			sb.append(TC);
			sb.append(": ");
			sb.append(M);
			sb.append(' ');
			sb.append(N);
			sb.append('\n');
			sb.append("Output");
			sb.append(TC);
			sb.append(':');
			int temp=maxI;
			while (true) {
				sb.append(' ');
				sb.append(temp);
				int next=getNext(temp);
				if (next==temp) break;
				temp=next;
			}

			System.out.println(sb.toString());
			TC++;
		}
	}

}
