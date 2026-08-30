import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long [] AdjList;
	private static int Ans;

	private static void find(int curr, int putCount, long coverMask) {
		if (putCount>=Ans) return;
		if (coverMask==(1L<<AdjList.length)-1) Ans=Math.min(Ans,putCount);
		if (curr==AdjList.length) return;

		long currMask=1L<<curr;
		// Try put if can cover extra
		long nCovMask=coverMask|AdjList[curr]|currMask;
		if (coverMask!=nCovMask) find(curr+1,putCount+1,nCovMask);

		// Try skip if can be covered by later nodes / already covered now
		long laterMask=((1L<<AdjList.length)-1)-((1<<curr)-1);
		if ((AdjList[curr]&laterMask)!=0 || (coverMask&currMask)!=0) {
			find(curr+1,putCount,coverMask);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			AdjList=new long [N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				AdjList[n1]|=(1L<<n2);
				AdjList[n2]|=(1L<<n1);
			}

			Ans=N;
			find(0,0,0);
			System.out.println(Ans);
			br.readLine();
		}
	}

}