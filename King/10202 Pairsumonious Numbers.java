import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class zz {

	private static int [] Nums;
	private static boolean [] Visited;
	private static int [] Ans;

	private static void find(int [] curr, int n) {
		if (Ans!=null) return;
		if (n==curr.length) {
			int [] end=new int [Nums.length];
			int endIdx=0;
			for (int i=0;i<curr.length;i++) for (int i2=i+1;i2<curr.length;i2++) end[endIdx++]=curr[i]+curr[i2];
			Arrays.sort(end);

			if (Arrays.equals(Nums,end)) Ans=Arrays.copyOf(curr,n);
			return;
		}
		for (int i=0;i<Nums.length;i++) if (!Visited[i]) {
			int i2=Nums[i]-curr[n-1];
			if (curr[n-1]<=i2) {
				curr[n]=i2;
				Visited[i]=true;
				find(curr,n+1);
				Visited[i]=false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Nums=new int[(N*(N-1))>>1];
			for (int n=0;n<Nums.length;n++) Nums[n]=Integer.parseInt(st.nextToken());
			Arrays.sort(Nums);
			Visited=new boolean [Nums.length];

			/*
			 * N0 = X0+X1
			 * N1 = X0+X2
			 * 
			 * X0 + X1 + X0 + X2 + X1 + X2 = 2(X0 + X1 + X2)
			 * N0 + N1 + N2 = 2(X0 + N2)
			 * 
			 *       N0 + N1 - N2
			 * X0 = ---------------
			 *            2
			 * 
			 * For loop Nums as N2 to get X0 and search.
			 */
			Ans=null;
			int [] temp=new int [N];
			for (int i=2;i<N && Ans==null;i++) {
				temp[0]=(Nums[0]+Nums[1]-Nums[i]);
				if ((temp[0]&1)==1) continue;
				temp[0]>>=1;
				find(temp,1);
			}

			if (Ans==null) System.out.println("Impossible");
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<Ans.length;i++) {
					sb.append(Ans[i]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb);
			}
		}
	}

}