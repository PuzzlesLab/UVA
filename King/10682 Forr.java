import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Main {

	private static class Edge {
		int next,vIdx;
		
		public Edge(int n, int vIdx) {
			this.next=n;
			this.vIdx=vIdx;
		}
	}

	private static class State {
		int c,vIdx,remBrake;
		State parent;
		
		public State(int c, int vIdx, int r, State p) {
			this.c=c;
			this.vIdx=vIdx;
			this.remBrake=r;
			this.parent=p;
		}
	}

	private static int cmpArray(int [] left, int [] right) {
		if (left.length!=right.length) return left.length-right.length;

		for (int i=0;i<left.length;i++) if (left[i]!=right[i]) return left[i]-right[i];
		return 0;
	}


	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		boolean first=true;
		while (sc.hasNext()) {
			int R=Integer.parseInt(sc.next());

			HashMap<String,Integer> cIdxMap=new HashMap<>();
			ArrayList<String> cities=new ArrayList<>();
			ArrayList<String []> roads=new ArrayList<>();
			HashSet<Integer> speedSet=new HashSet<>();
			for (int r=0;r<R;r++) {
				String [] edge=new String [] {sc.next(),sc.next(),sc.next()};
				roads.add(edge);

				if (!cIdxMap.containsKey(edge[0])) {
					cIdxMap.put(edge[0],cities.size());
					cities.add(edge[0]);
				}
				if (!cIdxMap.containsKey(edge[1])) {
					cIdxMap.put(edge[1],cities.size());
					cities.add(edge[1]);
				}
				speedSet.add(Integer.parseInt(edge[2]));
			}
			ArrayList<Integer> speedList=new ArrayList<>(speedSet);
			Collections.sort(speedList);
			HashMap<Integer,Integer> speedIdxMap=new HashMap<>();
			for (int i=0;i<speedList.size();i++) speedIdxMap.put(speedList.get(i),i);

			String startC=sc.next();
			if (!cIdxMap.containsKey(startC)) {
				cIdxMap.put(startC,cities.size());
				cities.add(startC);
			}
			String endC=sc.next();
			if (!cIdxMap.containsKey(endC)) {
				cIdxMap.put(endC,cities.size());
				cities.add(endC);
			}

			ArrayList<Edge> [] adjList=new ArrayList [cities.size()];
			for (int i=0;i<adjList.length;i++) adjList[i]=new ArrayList<>();
			for (int r=0;r<R;r++) {
				String [] edge=roads.get(r);
				int from=cIdxMap.get(edge[0]);
				int to=cIdxMap.get(edge[1]);
				int v=Integer.parseInt(edge[2]);
				adjList[from].add(new Edge(to,speedIdxMap.get(v)));
				adjList[to].add(new Edge(from,speedIdxMap.get(v)));
			}
			
			int start=cIdxMap.get(startC);
			int end=cIdxMap.get(endC);
			LinkedList<State> q=new LinkedList<>();
			boolean [][][] visited=new boolean [cities.size()][speedList.size()][2];
			q.addLast(new State(start,0,0,null));
			visited[start][0][0]=true;
			
			int [] sol=null;
			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.c==end && curr.remBrake==0) {
					State temp=curr;
					Stack<Integer> stk=new Stack<>();
					while (temp!=null) {
						stk.push(temp.c);
						temp=temp.parent;
					}
					int [] currSol=new int [stk.size()];
					for (int i=0;i<currSol.length;i++) currSol[i]=stk.pop();
					if (sol==null || cmpArray(sol,currSol)>0) {
						sol=currSol;
					}
					continue;
				}
				for (int i=0;i<adjList[curr.c].size();i++) {
					Edge e=adjList[curr.c].get(i);
					State nS=new State(e.next,e.vIdx,(e.vIdx<curr.vIdx)?curr.remBrake-1:curr.remBrake,curr);
					if (nS.remBrake>=0 && !visited[nS.c][nS.vIdx][nS.remBrake]) {
						visited[nS.c][nS.vIdx][nS.remBrake]=true;
						q.addLast(nS);
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (first) first=false;
			else sb.append('\n');
			if (sol==null) sb.append("No valid route.");
			else {
				for (int i=0;i<sol.length;i++) {
					sb.append(cities.get(sol[i]));
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
			}
			System.out.println(sb);
		}
	}

}