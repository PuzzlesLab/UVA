import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Game;
	private static int [][][] Dp;
	private static State [][][] Next;
	private static int EndMask;

	private static class State {
		int gMask, score, f6Sum;
		
		public State(int gm, int s, int f) {
			this.gMask=gm;
			this.score=s;
			this.f6Sum=f;
		}
	}

	private static int sumOfN(int g, int n) {
		int r=0;
		for (int i=0;i<Game[g].length;i++) if (Game[g][i]==n) r+=n;
		return r;
	}
	
	private static int sum(int g) {
		int r=0;
		for (int i=0;i<Game[g].length;i++) r+=Game[g][i];
		return r;
	}

	private static boolean nOfAKind(int g, int n) {
		int c1=0;
		int c2=0;
		int c3=0;
		int c4=0;
		int c5=0;
		int c6=0;
		for (int i=0;i<Game[g].length;i++) {
			if (Game[g][i]==1) c1++;
			if (Game[g][i]==2) c2++;
			if (Game[g][i]==3) c3++;
			if (Game[g][i]==4) c4++;
			if (Game[g][i]==5) c5++;
			if (Game[g][i]==6) c6++;
		}
		return c1>=n || c2>=n || c3>=n || c4>=n || c5>=n || c6>=n;
	}
	
	private static int threeOfAKind(int g) {
		return nOfAKind(g,3)?sum(g):0;
	}
	
	private static int fourOfAKind(int g) {
		return nOfAKind(g,4)?sum(g):0;
	}
	
	private static int fiveOfAKind(int g) {
		return nOfAKind(g,5)?50:0;
	}
	
	private static int shortStr(int g) {
		boolean n1=false;
		boolean n2=false;
		boolean n3=false;
		boolean n4=false;
		boolean n5=false;
		boolean n6=false;
		
		for (int i=0;i<Game[g].length;i++) {
			if (Game[g][i]==1) n1=true;
			if (Game[g][i]==2) n2=true;
			if (Game[g][i]==3) n3=true;
			if (Game[g][i]==4) n4=true;
			if (Game[g][i]==5) n5=true;
			if (Game[g][i]==6) n6=true;
		}
		
		if (n1&&n2&&n3&&n4) return 25;
		if (n2&&n3&&n4&&n5) return 25;
		if (n3&&n4&&n5&&n6) return 25;
		return 0;
	}
	
	private static int longStr(int g) {
		boolean n1=false;
		boolean n2=false;
		boolean n3=false;
		boolean n4=false;
		boolean n5=false;
		boolean n6=false;
		
		for (int i=0;i<Game[g].length;i++) {
			if (Game[g][i]==1) n1=true;
			if (Game[g][i]==2) n2=true;
			if (Game[g][i]==3) n3=true;
			if (Game[g][i]==4) n4=true;
			if (Game[g][i]==5) n5=true;
			if (Game[g][i]==6) n6=true;
		}
		
		if (n1&&n2&&n3&&n4&&n5) return 35;
		if (n2&&n3&&n4&&n5&&n6) return 35;
		return 0;
	}

	private static int fullHouse(int g) {
		if (Game[g][0]==Game[g][1] && Game[g][1]!=Game[g][2] && Game[g][2]==Game[g][3] && Game[g][3]==Game[g][4]) return 40;
		if (Game[g][0]==Game[g][1] && Game[g][1]==Game[g][2] && Game[g][2]!=Game[g][3] && Game[g][3]==Game[g][4]) return 40;
		return 0;
	}

	private static int compute(int cat, int gMask, int f6Sum) {
		if (cat==13) {
			Dp[cat][gMask][f6Sum]=f6Sum>=63?35:0;
			Next[cat][gMask][f6Sum]=new State(gMask,Dp[cat][gMask][f6Sum],f6Sum);
			return Dp[cat][gMask][f6Sum];
		}
		if (gMask==EndMask) return 0;

		if (Dp[cat][gMask][f6Sum]==-1) {
			int ans=0;
			
			for (int g=0;g<Game.length;g++) if ((gMask&(1<<g))==0) {
				int nGMask=gMask|(1<<g);
				int delta=0;
				int nF6=f6Sum;
				if (cat<=5) {
					delta=sumOfN(g,cat+1);
					nF6=Math.min(nF6+delta, 63);
				}
				else if (cat==6) delta=sum(g);
				else if (cat==7) delta=threeOfAKind(g);
				else if (cat==8) delta=fourOfAKind(g);
				else if (cat==9) delta=fiveOfAKind(g);
				else if (cat==10) delta=shortStr(g);
				else if (cat==11) delta=longStr(g);
				else if (cat==12) delta=fullHouse(g);

				int curr=delta+compute(cat+1,nGMask,nF6);
				if (curr>=ans) {
					Next[cat][gMask][f6Sum]=new State(nGMask,delta,nF6);
					ans=curr;
				}
			}

			Dp[cat][gMask][f6Sum]=ans;
		}
		return Dp[cat][gMask][f6Sum];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	ArrayList<StringTokenizer> data=new ArrayList<>();
        	data.add(new StringTokenizer(s));
        	for (int i=0;i<12;i++) data.add(new StringTokenizer(br.readLine()));
        	
        	Game=new int [13][5];
        	for (int i=0;i<Game.length;i++) {
        		StringTokenizer st=data.get(i);
        		for (int i2=0;i2<Game[i].length;i2++) Game[i][i2]=Integer.parseInt(st.nextToken());
        		Arrays.sort(Game[i]); // Easier for comparison later.
        	}
        	
        	EndMask=(1<<Game.length)-1;
        	Dp=new int [14][EndMask+1][64];
        	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++)
        		Arrays.fill(Dp[i][i2],-1);
        	Next=new State[14][EndMask+1][64];
        	
        	int ans=compute(0,0,0);
        	State currSt=Next[0][0][0];
        	StringBuilder sb=new StringBuilder();
        	for (int cat=0;cat<14;cat++) {
        		sb.append(currSt.score);
        		sb.append(' ');
        		if (cat<13) currSt=Next[cat+1][currSt.gMask][currSt.f6Sum];
        	}
        	sb.append(ans);
        	System.out.println(sb);
        }
	}

}