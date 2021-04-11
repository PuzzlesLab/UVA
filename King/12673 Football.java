import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static class Match implements Comparable<Match> {
		int S, R, diff;
		
		public Match(int s, int r) {
			this.S=s;
			this.R=r;
			this.diff=this.R-this.S;
		}
		
		public int compareTo(Match m) {
			return this.diff-m.diff;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int G=Integer.parseInt(st.nextToken());
			
			int matchesWon=0;
			int matchesTie=0;
			ArrayList<Match> matchesToBuy=new ArrayList<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int ms=Integer.parseInt(st.nextToken());
				int mr=Integer.parseInt(st.nextToken());
				if (ms>mr) matchesWon++;
				else matchesToBuy.add(new Match(ms,mr));
			}
			
			Collections.sort(matchesToBuy);
			for (Match m : matchesToBuy) {
				if (G>0) {
					int buyToTie=m.R-m.S;
					int buyToWin=buyToTie+1;
					if (G>=buyToWin) {
						G-=buyToWin;
						m.S+=buyToWin;
					} else if (G>=buyToTie) {
						G-=buyToTie;
						m.S+=buyToTie;
					}
				}
				if (m.S>m.R) matchesWon++;
				else if (m.S==m.R) matchesTie++;
			}
			
			System.out.println(matchesWon*3+matchesTie);
		}
	}
}