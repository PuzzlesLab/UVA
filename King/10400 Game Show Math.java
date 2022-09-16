import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main { 

	private static int [] Nums;
	private static int Target;
	private static int [][] Dp;
	private static char [] Solution;

	private static boolean find(int n, int curr, char [] trace) {
		if (n==Nums.length) {
			if (curr==Target && Solution==null) Solution=Arrays.copyOf(trace,trace.length);
			return curr==Target;
		}

		int dpIdx=curr+32000;
		if (Dp[n][dpIdx]==0) {
			boolean flag=false;
			int value=0;

			value=curr+Nums[n];
			if (Math.abs(value)<32000) {
				trace[n-1]='+';
				flag|=find(n+1,value,trace);
			}

			value=curr-Nums[n];
			if (Math.abs(value)<32000) {
				trace[n-1]='-';
				flag|=find(n+1,value,trace);
			}
			
			value=curr*Nums[n];
			if (Math.abs(value)<32000) {
				trace[n-1]='*';
				flag|=find(n+1,value,trace);
			}
	
			if (curr%Nums[n]==0) {
				value=curr/Nums[n];
				if (Math.abs(value)<32000) {
					trace[n-1]='/';
					flag|=find(n+1,value,trace);
				}
			}
			Dp[n][dpIdx]=flag?1:2;
		}
		
		return Dp[n][dpIdx]==1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			Nums=new int [N];
			for (int n=0;n<Nums.length;n++) Nums[n]=Integer.parseInt(st.nextToken());
			Target=Integer.parseInt(st.nextToken());
			
			Dp=new int [N+1][64001];
			Solution=null;
			boolean result=find(1,Nums[0],new char [N+1]);
			if (!result) System.out.println("NO EXPRESSION");
			else {
				StringBuilder sb=new StringBuilder();
				for (int n=0;n<Nums.length;n++) {
					sb.append(Nums[n]);
					if (Solution[n]!=0) sb.append(Solution[n]);
				}
				sb.append("=");
				sb.append(Target);
				System.out.println(sb.toString());
			}
		}
	}

}
