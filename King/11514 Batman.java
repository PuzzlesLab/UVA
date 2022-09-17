import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static class Power {
		String name;
		int a, c;
		HashSet<String> affects;
	}

	private static final int MAX=1000000000;
	private static Power [] Powers;
	private static String [] Villains;
	private static final int DP_UNSOLVED=-1;
	private static int [][] Dp;
	
	public static int compute(int p, int v) {
		if (v==Villains.length) return 0;
		if (p==Powers.length) return MAX;

		if (Dp[p][v]==DP_UNSOLVED) {
			int min=MAX;
			// Choose
			Power currP=Powers[p];
			if (currP.affects.contains(Villains[v])) {
				min=Math.min(min,currP.c+compute(p+1,v+1));
			}
			// Don't choose.
			min=Math.min(min,compute(p+1,v));

			Dp[p][v]=min;
		}

		return Dp[p][v];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int V=Integer.parseInt(st.nextToken());
			long E=Long.parseLong(st.nextToken());
			
			HashMap<String,Power> powerMap=new HashMap<>();
			Powers=new Power[P];
			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				Powers[p]=new Power();
				Powers[p].name=st.nextToken();
				Powers[p].a=Integer.parseInt(st.nextToken());
				Powers[p].c=Integer.parseInt(st.nextToken());
				Powers[p].affects=new HashSet<String>();
				powerMap.put(Powers[p].name,Powers[p]);
			}
			
			Villains=new String [V];
			for (int v=0;v<V;v++) {
				st=new StringTokenizer(br.readLine());
				Villains[v]=st.nextToken();
				int df=Integer.parseInt(st.nextToken());

				st=new StringTokenizer(st.nextToken(),",");
				while (st.hasMoreTokens()) {
					Power p=powerMap.get(st.nextToken());
					if (p.a>=df) p.affects.add(Villains[v]);
				}
			}

			Dp=new int [P+1][V+1];
			for (int p=0;p<Dp.length;p++) Arrays.fill(Dp[p],DP_UNSOLVED);

			System.out.println(compute(0,0)<=E ? "Yes": "No");
		}
	}

}
