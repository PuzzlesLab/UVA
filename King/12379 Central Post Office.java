import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static ArrayList<Integer> [] AdjList;
	private static int [] Parent;

	private static class Tuple {
		int node, dist;
		
		public Tuple(int node, int dist) {
			this.node=node;
			this.dist=dist;
		}
	}

	private static Tuple findLast(int start) {
		boolean [] visited=new boolean [N];
		visited[start]=true;
		Parent=new int [N];
		
		Tuple sol=null;
		LinkedList<Tuple> q=new LinkedList<>();
		q.add(new Tuple(start,0));
		while (!q.isEmpty()) {
			Tuple curr=q.removeFirst();
			sol=curr;
			for (int i=0;i<AdjList[curr.node].size();i++) {
				int next=AdjList[curr.node].get(i);
				if (visited[next]) continue;
				
				visited[next]=true;
				Parent[next]=curr.node;
				q.add(new Tuple(next,curr.dist+1));
			}
		}
		
		return sol;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			N=Integer.parseInt(br.readLine());
			AdjList=new ArrayList [N];
			for (int n=0;n<N;n++) {
				AdjList[n]=new ArrayList<>();
				
				StringTokenizer st=new StringTokenizer(br.readLine());
				int I=Integer.parseInt(st.nextToken());
				for (int i=0;i<I;i++) AdjList[n].add(Integer.parseInt(st.nextToken())-1);
			}
			
			Tuple last=findLast(0);
			Tuple last2=findLast(last.node);
			int total=(N-1)<<1;
			System.out.println(total-last2.dist);
		}
	}

}
