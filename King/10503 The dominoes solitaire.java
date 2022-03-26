import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean Ans=false;

	private static class Domino {
		int n1, n2;
		boolean used;
		
		public Domino(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.n1=Integer.parseInt(st.nextToken());
			this.n2=Integer.parseInt(st.nextToken());
		}
	}

	private static void find(int lastNum, int selectedCount, int max, Domino [] candidates) {
		if (Ans) return;

		if (selectedCount==0) {
			candidates[0].used=true;
			find(candidates[0].n2,selectedCount+1,max,candidates);
			candidates[0].used=false;
		} else if (selectedCount==max-1) {
			Ans = lastNum == candidates[candidates.length-1].n1;
			if (Ans) return;
		} else {
			for (int i=1;i<candidates.length-1;i++) if (!candidates[i].used) {
				if (lastNum==candidates[i].n1) {
					candidates[i].used=true;
					find(candidates[i].n2,selectedCount+1,max,candidates);
					candidates[i].used=false;
				} else if (lastNum==candidates[i].n2) {
					candidates[i].used=true;
					find(candidates[i].n1,selectedCount+1,max,candidates);
					candidates[i].used=false;
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int M=Integer.parseInt(br.readLine());
			
			Domino [] candidates=new Domino [M+2];
			candidates[0]=new Domino(br.readLine());
			candidates[M+1]=new Domino(br.readLine());
			for (int m=1;m<=M;m++) candidates[m]=new Domino(br.readLine());

			Ans=false;
			find(-1,0,N+2,candidates);
			
			System.out.println(Ans?"YES":"NO");
		}
	}

}
