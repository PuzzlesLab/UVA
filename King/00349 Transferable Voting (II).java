import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Vote {
		private LinkedList<Candidate> priorityList=new LinkedList<>();
		
		public void addCandidate(Candidate c, int location) {
			priorityList.add(c);
			c.voteList.add(this);
			c.recalculateRank();
		}
		
		public void removeCandidate(Candidate c) {
			priorityList.remove(c);
			c.voteList.remove(this);
		}
		
		public int candidateIndex(Candidate c) {
			return priorityList.indexOf(c);
		}
		
		public void propagateRank() {
			for (Candidate c : priorityList) c.recalculateRank();
		}
	}
	
	private static class Candidate implements Comparable<Candidate> {
		public int id;
		public int [] rank=new int [5];
		public ArrayList<Vote> voteList=new ArrayList<>();
		
		public Candidate(int id) {
			this.id=id;
		}
		
		public void recalculateRank() {
			this.rank=new int [5];
			for (Vote v : voteList) this.rank[v.candidateIndex(this)]++;
		}
		
		public void destroy() {
			while (!voteList.isEmpty()) {
				Vote v=voteList.get(0);
				v.removeCandidate(this);
				v.propagateRank();
			}
		}
		
		public int compareTo(Candidate c) {
			for (int i=0;i<this.rank.length;i++) if (this.rank[i]!=c.rank[i]) return this.rank[i]-c.rank[i];
			return 0;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		for (int testCount=1;!(s=br.readLine()).equals("0 0");testCount++) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			HashMap<Integer,Candidate> candidates=new HashMap<>();
			for (int c=1;c<=C;c++) candidates.put(c, new Candidate(c));
			ArrayList<Vote> votes=new ArrayList<>();
			for (int n=0;n<N;n++) {
				s=br.readLine();
				st=new StringTokenizer(s);
				ArrayList<Integer> choices=new ArrayList<>();
				boolean isValid=true;
				while (st.hasMoreTokens()) {
					int cid = Integer.parseInt(st.nextToken());
					if (cid>=1 && cid<=C) choices.add(cid-1);
				}
				
				isValid &= choices.size() == C;
				if (isValid) {
					int [] voteTable = new int [C+1];
					for (int c=0;c<choices.size();c++) voteTable[choices.get(c)]++;
					for (int c=0;c<C;c++) isValid &= (voteTable[c]==1);
				}
				
				if (isValid) {
					Vote v=new Vote();
					for (int c=0;c<C;c++) v.addCandidate(candidates.get(choices.get(c)+1), c);
					votes.add(v);
				}
				
			}
			
			int badBallotCount=N-votes.size();
			ArrayList<Candidate> cList=new ArrayList<>(candidates.values());
			Collections.sort(cList);
			while (cList.size()>1 && cList.get(0).compareTo(cList.get(cList.size()-1))<0) {
				cList.get(0).destroy();
				cList.remove(0);
				Collections.sort(cList);
			}
			
			System.out.println("Election #"+testCount);
			System.out.println("   "+badBallotCount+" bad ballot(s)");
			if (cList.size()==1) { System.out.println("   Candidate "+cList.get(0).id+" is elected.");
			} else {
				StringBuilder sb=new StringBuilder();
				sb.append("   The following candidates are tied:");
				for (Candidate c : cList) {
					sb.append(" ");
					sb.append(c.id);
				}
				System.out.println(sb.toString());
			}
		}
	}

}