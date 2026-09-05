import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> Mats=new ArrayList<>();
	private static int [][] Dp;

	private static int computeHelper(int l, int r) {
		if (l>r) return 0;

		if (Dp[l][r]==-1) {
			int ans=1+computeHelper(l+1,r);
			for (int mid=l+1;mid<=r;mid++) if (Mats.get(mid)==Mats.get(l)) {
				ans=Math.min(ans,computeHelper(l+1,mid-1)+computeHelper(mid,r));
			}
			Dp[l][r]=ans;
		}

		return Dp[l][r];
	}

	private static int compute() {
		int N=Mats.size();
		if (N==0) return 0;
    	Dp=new int [N][N];
    	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
    	return computeHelper(0,N-1);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	int N=Integer.parseInt(br.readLine());
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	HashMap<String,Integer> nameIdxMap=new HashMap<>();
        	int nameIdxMax=0;
        	int [] names=new int [N];
        	boolean [] bound=new boolean [N];
        	for (int n=0;n<N;n++) {
        		String s=st.nextToken();
        		if (!nameIdxMap.containsKey(s)) nameIdxMap.put(s,nameIdxMax++);
        		names[n]=nameIdxMap.get(s);
        		bound[n]=Character.isUpperCase(s.charAt(0));
        	}

        	// Since we can't remove BIG material, so merging + remove in 1 step is not possible cross BIG material.
        	// We process recyclable group by group.
        	Mats.clear();
        	int ans=0;
        	for (int n=0;n<N;n++) {
        		if (bound[n]) {
        			ans+=compute();
        			Mats.clear();
        		} else if (Mats.isEmpty() || names[n]!=Mats.get(Mats.size()-1)) {
        			Mats.add(names[n]);
        		}
        	}
        	ans+=compute();

        	System.out.printf("Case %d: %d\n",tc,ans);
        }
	}

}