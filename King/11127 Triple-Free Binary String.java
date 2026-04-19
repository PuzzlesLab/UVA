import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int Ans;

	private static boolean possible(char [] P) {
		int maxSize=P.length/3;
		for (int size=1;size<=maxSize;size++) {
			for (int start=0;start+3*size<=P.length;start++) {
				int g1=start;
				int g2=start+size;
				int g3=start+size+size;
				
				boolean allMatch=true;
				for (int d=0;d<size && allMatch;d++) {
					if (P[g1+d]=='*' || P[g2+d]=='*' || P[g3+d]=='*') {
						allMatch=false;
						break;
					}
					allMatch&=P[g1+d]==P[g2+d];
					allMatch&=P[g2+d]==P[g3+d];
				}
				if (allMatch) return false;
			}
		}
		return true;
	}

	private static void dfs(char [] P, int curr, int mask) {
		if (curr==P.length) {
			if (possible(P)) Ans++;
			return;
		}

		if ((mask&(1<<curr))!=0) {
			dfs(P,curr+1,mask);
			return;
		}

		P[curr]='0';
		if (possible(P)) dfs(P,curr+1,mask|(1<<curr));
		P[curr]='1';
		if (possible(P)) dfs(P,curr+1,mask|(1<<curr));
		P[curr]='*';
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			st.nextToken(); // N.
			char [] P=st.nextToken().trim().toCharArray();

			int mask=0;
			for (int i=0;i<P.length;i++) if (P[i]!='*') mask|=(1<<i);
			Ans=0;
			dfs(P,0,mask);

			System.out.printf("Case %d: %d\n",tc++,Ans);
		}
	}

}