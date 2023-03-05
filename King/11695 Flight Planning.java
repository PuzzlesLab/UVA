import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static LinkedList<Integer> [] AdjList;
	private static int [] Parent;

	private static class Tuple {
		int a, b;
		
		public Tuple(int a, int b) {
			this.a=a;
			this.b=b;
		}
		
		public boolean equivalent(int from, int to) {
			return (this.a==from && this.b==to) || (this.a==to && this.b==from);
		}
		
		public boolean equivalent(Tuple t) {
			if (t==null) return false;
			return (this.a==t.a && this.b==t.b) || (this.a==t.b && this.b==t.a);
		}
	}

	private static Tuple getFurthest(int start, Tuple blocked, Tuple added) {
		Tuple sol=null;
		boolean [] visited=new boolean [N];
		Parent=new int [N];
		Arrays.fill(Parent,-1);
		
		LinkedList<Tuple> q=new LinkedList<>();
		q.addLast(new Tuple(start,1));
		visited[start]=true;
		while (!q.isEmpty()) {
			Tuple curr=q.removeFirst();
			if (sol==null || curr.b>sol.b) sol=curr;

			if (added!=null) {
				if (curr.a==added.a) AdjList[curr.a].addLast(added.b);
				if (curr.a==added.b) AdjList[curr.a].addLast(added.a);
			}
			
			for (int next: AdjList[curr.a]) if (!visited[next] && (blocked.equivalent(added) || !blocked.equivalent(curr.a,next))) {
				visited[next]=true;
				Parent[next]=curr.a;
				q.addLast(new Tuple(next,curr.b+1));
			}

			if (added!=null && (curr.a==added.a || curr.a==added.b)) AdjList[curr.a].removeLast();
		}

		return sol;
	}

	private static Tuple computeDiameter(int start, Tuple blocked, Tuple added) {
		Tuple sol1=getFurthest(start,blocked,added);
		Tuple sol2=getFurthest(sol1.a,blocked,added);

		int temp=sol2.a;
		for (int n=0;n<sol2.b/2;n++) temp=Parent[temp];
		return new Tuple(temp,sol2.b); // Midpoint, full length
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			N=Integer.parseInt(br.readLine());
			AdjList=new LinkedList [N];
			for (int n=0;n<N;n++) AdjList[n]=new LinkedList<>();

			Tuple [] edges=new Tuple [N-1];
			for (int n=0;n<N-1;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				AdjList[a].add(b);
				AdjList[b].add(a);
				edges[n]=new Tuple(a,b);
			}

			int best=Integer.MAX_VALUE;
			Tuple toRemove=null;
			Tuple toAdd=null;
			for (int n=0;n<N-1;n++) {
				Tuple diaA=computeDiameter(edges[n].a,edges[n],null);
				Tuple diaB=computeDiameter(edges[n].b,edges[n],null);
				Tuple newEdge=new Tuple(diaA.a,diaB.a);
				Tuple diaC=computeDiameter(edges[n].a,edges[n],newEdge);

				int curr=diaC.b-1;
				if (curr<best) {
					best=curr;
					toRemove=edges[n];
					toAdd=newEdge;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append(best);
			sb.append('\n');

			sb.append(toRemove.a+1);
			sb.append(' ');
			sb.append(toRemove.b+1);
			sb.append('\n');

			sb.append(toAdd.a+1);
			sb.append(' ');
			sb.append(toAdd.b+1);

			System.out.println(sb.toString());
		}
	}

}
