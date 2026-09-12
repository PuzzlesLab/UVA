import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Acorns;
	private static int [][] AcornCount;
	private static int F;
	private static int [][] Dp;
	private static int [] DpAny;

	private static int compute(int tree, int h) {
		if (h<0) return 0;

		if (Dp[tree][h]==-1) {
			// Slide down to next acorn.
			int ans=compute(tree,h-1);
			// To any other tree
			if (DpAny[h]==-1) {
				int temp=0;
				for (int nt=0;nt<Acorns.length;nt++) temp=Math.max(temp,compute(nt,h-F));
				DpAny[h]=temp;
			}
			ans=Math.max(ans,DpAny[h]);

			Dp[tree][h]=AcornCount[tree][h]+ans; // Include acorns at curr height.
		}

		return Dp[tree][h];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int T=Integer.parseInt(st.nextToken());
        	int H=Integer.parseInt(st.nextToken());
        	F=Integer.parseInt(st.nextToken());

        	Acorns=new int [T][];
        	AcornCount=new int [T][H+F+1];
        	for (int t=0;t<T;t++) {
        		st=new StringTokenizer(br.readLine());
        		Acorns[t]=new int [Integer.parseInt(st.nextToken())];
        		for (int i=0;i<Acorns[t].length;i++) {
        			int h=Integer.parseInt(st.nextToken());
        			Acorns[t][i]=h;
        			AcornCount[t][h]++;
        		}
        	}

        	Dp=new int [T][H+F+1];
        	for (int t=0;t<T;t++) Arrays.fill(Dp[t],-1);
        	DpAny=new int [H+F+1];
        	Arrays.fill(DpAny,-1);
        	// We assume from H+F from the first tree, then drop H-F to reach any tree.
        	System.out.println(compute(0,H+F));
        }
	}

}