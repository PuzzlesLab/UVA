import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Team implements Comparable<Team> {
		int problemSolved;
		int [] penalty;
		boolean [] solved;
		int no, m;
		
		public Team (int no) {
			this.no=no;
			this.penalty=new int ['G'-'A'+1];
			this.solved=new boolean['G'-'A'+1];
		}
		
		public int compareWithoutNo(Team arg0) {
			int [] myData= {this.problemSolved, arg0.m};
			int [] arg0Data= {arg0.problemSolved, this.m};
			for (int i=0;i<myData.length;i++) if (myData[i]!=arg0Data[i]) return arg0Data[i]-myData[i];
			return 0;
		}
		
		@Override
		public int compareTo(Team arg0) {
			int delta=this.compareWithoutNo(arg0);
			if (delta!=0) return delta;
			return this.no-arg0.no;
		}
		
	}
	
	private static class Entry implements Comparable<Entry> {
		int no, q, m;
		boolean ac;
		
		public Entry (StringTokenizer st) {
			this.no=Integer.parseInt(st.nextToken());
			this.q=st.nextToken().charAt(0)-'A';
			String [] ttime=st.nextToken().split(":");
			this.m=Integer.parseInt(ttime[0])*60+Integer.parseInt(ttime[1]);
			this.ac=st.nextToken().charAt(0)=='Y';
		}
		
		@Override
		public int compareTo(Entry arg0) {
			int a=this.m-arg0.m;
			if (a!=0) return a;
			else if (this.ac ^ arg0.ac) return (this.ac) ? 1 : -1;
			return 0;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			String s;
			
			ArrayList<Entry> entries=new ArrayList<>();
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				entries.add(new Entry(new StringTokenizer(s)));
			}
			Collections.sort(entries);
			
			Team [] team=new Team[26];
			for (Entry e : entries) {
				Team t=team[e.no];
				if (t==null) {
					for (int i=1;i<=e.no;i++) if (team[i]==null) team[i]=new Team(i);
					t=team[e.no];
				}
				
				if (e.ac && !t.solved[e.q]) {
					t.solved[e.q]=true;
					t.problemSolved++;
					t.m=t.m+e.m+t.penalty[e.q];
				} else if (!e.ac && !t.solved[e.q]) t.penalty[e.q]+=20;
			}
			
			ArrayList<Team> validTeams=new ArrayList<>();
			for (Team t : team) if (t!=null) validTeams.add(t);
			Collections.sort(validTeams);
			
			StringBuilder sb=new StringBuilder();
			sb.append("RANK TEAM PRO/SOLVED TIME\n");
			int rank=0;
			for (int i=0;i<validTeams.size();i++) {
				Team t=validTeams.get(i);
				if (i==0 || t.compareWithoutNo(validTeams.get(i-1))>0) rank=Math.max(rank+1, i+1);
				if (t.problemSolved>0) sb.append(String.format("%4d %4d %4d %10d\n",rank,t.no,t.problemSolved,t.m));
				else sb.append(String.format("%4d %4d\n",rank,t.no));
			}
			System.out.print(sb.toString());
			if (testCase<testCaseCount-1) System.out.println();
		}

	}

}
