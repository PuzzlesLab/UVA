import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static HashMap<Integer,Integer> [] Edges;
	private static int [] DpExistsFriendBelow;
	private static boolean [] IsFriend;
	private static int MaxDist;
	private static int TotalDist;

	private static boolean existsFriendBelow(int curr) {
		if (IsFriend[curr]) return true;
		
		if (DpExistsFriendBelow[curr]==0) {
			boolean flag=false;
			for (int next: Edges[curr].keySet()) {
				flag|=existsFriendBelow(next);
				if (flag) break;
			}
			DpExistsFriendBelow[curr]=flag?1:2;
		}

		return DpExistsFriendBelow[curr]==1;
	}

	private static void compute(int curr, int currDist) {
		if (IsFriend[curr]) MaxDist=Math.max(MaxDist,currDist);

		for (int next: Edges[curr].keySet()) if (existsFriendBelow(next)) {
			compute(next,currDist+Edges[curr].get(next));
			TotalDist+=Edges[curr].get(next);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int F=Integer.parseInt(st.nextToken());

			Edges=new HashMap [N];
			for (int n=0;n<N;n++) Edges[n]=new HashMap<>();


			for (int i=0;i<N-1;i++) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				int C=Integer.parseInt(st.nextToken());
				Edges[A].put(B,C);
			}

			IsFriend=new boolean [N];
			st=new StringTokenizer(br.readLine());
			for (int f=0;f<F;f++) IsFriend[Integer.parseInt(st.nextToken())-1]=true;

			DpExistsFriendBelow=new int [N];
			MaxDist=0;
			TotalDist=0;
			compute(0,0);
			System.out.println(TotalDist-MaxDist); // We don't climb back to peak from the longest path
		}
	}

}
