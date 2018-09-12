import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	private static class Team implements Comparable<Team> {
		int win, draw, loss, goalGain, goalLoss, gamePlayed;
		String name;
		
		public Team (String n) {
			this.name=n;
		}
		
		public int getScore() {
			return win*3+draw;
		}
		
		public int goalDiff() {
			return goalGain-goalLoss;
		}

		public String getScorePercentageStr() {
			if (this.gamePlayed==0) return "N/A";
			return String.format("%.2f", (this.getScore()*100.0)/(this.gamePlayed*3));
		}
		
		public int compareToWithoutName(Team arg0) {
			int [] myData=new int [] {this.getScore(), this.goalDiff(), this.goalGain};
			int [] arg0Data=new int [] {arg0.getScore(), arg0.goalDiff(), arg0.goalGain};
			
			for (int i=0;i<myData.length;i++) if (myData[i]!=arg0Data[i]) return arg0Data[i]-myData[i];
			return 0;
		}
		
		@Override
		public int compareTo(Team arg0) {
			int delta=compareToWithoutName(arg0);
			if (delta!=0) return delta;
			return this.name.toLowerCase().compareTo(arg0.name.toLowerCase());
		}
		
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean firstLine=true;
		while (!(s=br.readLine()).equals("0 0")) {
			if (!firstLine) System.out.println();
			else firstLine=false;
			
			StringTokenizer st=new StringTokenizer(s);
			int T=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			HashMap<String, Team> map=new HashMap<>();
			for (int t=0;t<T;t++) {
				s=br.readLine();
				map.put(s, new Team(s));
			}
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				String tA=st.nextToken();
				int tAScore=Integer.parseInt(st.nextToken());
				st.nextToken();// " - "
				int tBScore=Integer.parseInt(st.nextToken());
				String tB=st.nextToken();
				
				Team ttA=map.get(tA), ttB=map.get(tB);
				ttA.gamePlayed++; ttB.gamePlayed++;
				
				if (tAScore>tBScore) {
					ttA.win++; ttB.loss++;
				} else if (tAScore==tBScore) {
					ttA.draw++; ttB.draw++;
				} else {
					ttA.loss++; ttB.win++;
				}
				
				ttA.goalGain+=tAScore; ttA.goalLoss+=tBScore;
				ttB.goalGain+=tBScore; ttB.goalLoss+=tAScore;

			}
			
			Team [] teams=new Team[map.size()]; int x=0;
			for (Team t : map.values()) teams[x++]=t;
			Arrays.sort(teams);
			
			for (int i=0;i<teams.length;i++) {
				String currRank="";
				if (i==0 || teams[i].compareToWithoutName(teams[i-1])!=0) {
					currRank=(i+1)+".";
				}
				
				s = String.format("%3s%16s%4d%4d%4d%4d%4d%7s", currRank, teams[i].name, teams[i].getScore(),
							teams[i].gamePlayed, teams[i].goalGain, teams[i].goalLoss,
							teams[i].goalDiff(), teams[i].getScorePercentageStr());
				
				System.out.println(s);
			}
		}
	}

}