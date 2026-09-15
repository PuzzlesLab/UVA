import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Teacher {
		int cost;
		ArrayList<Integer> subjects;
		
		public Teacher(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.cost=Integer.parseInt(st.nextToken());
			this.subjects=new ArrayList<>();
			while (st.hasMoreTokens()) {
				this.subjects.add(Integer.parseInt(st.nextToken())-1);
			}
		}
	}
	
	private static final int MAX=100000000;
	private static Teacher [] Candidates;
	private static int S;
	private static int M;
	private static int [][] Dp;
	private static int EndMask;

	private static int fillSubMask(int subMask, Teacher t) {
		int nSubMask=subMask;
		for (int i=0;i<t.subjects.size();i++) {
			int subId=t.subjects.get(i);
			int subId2=subId+S;

			if ((nSubMask&(1<<subId))==0) nSubMask|=(1<<subId); // Fill subject n's first place.
			else if ((nSubMask&(1<<subId2))==0) nSubMask|=(1<<subId2); // Fill subject n's second place.
			// else the subject already full.
		}
		return nSubMask;
	}

	private static int compute(int subMask, int tId) {
		if (subMask==EndMask) return 0;
		if (tId==Candidates.length) return MAX;

		if (Dp[subMask][tId]==-1) {
			Teacher t=Candidates[tId];
			Dp[subMask][tId]=Math.min(compute(subMask,tId+1),t.cost+compute(fillSubMask(subMask,t),tId+1));
		}
		return Dp[subMask][tId];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	S=Integer.parseInt(st.nextToken());
        	if (S==0) break;

        	M=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken());

        	int initSubMask=0;
        	int initCost=0;
        	for (int m=0;m<M;m++) {
        		Teacher t=new Teacher(br.readLine());
        		initSubMask=fillSubMask(initSubMask,t);
        		initCost+=t.cost;
        	}

        	Candidates=new Teacher[N];
        	for (int n=0;n<Candidates.length;n++) Candidates[n]=new Teacher(br.readLine());

        	int S2=S<<1;
        	EndMask=(1<<S2)-1;
        	Dp=new int [1<<S2][Candidates.length];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        	System.out.println(initCost+compute(initSubMask,0));
        }
	}

}