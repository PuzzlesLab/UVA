import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static final int ANS_MAX=1000000;
	private static Castle [] Castles;
	private static int N;
	private static int Ans;

	private static class Castle {
		int in,out;
		ArrayList<Integer> adjList;
		
		public Castle(int a, int m, int g) {
			this.in=Math.max(a,m+g);
			this.out=m+g;
			this.adjList=new ArrayList<>();
		}
	}

	private static class Tuple implements Comparable<Tuple> {
		int in, out;
		
		public Tuple(int in, int out) {
			this.in=in;
			this.out=out;
		}
		
		public int compareTo(Tuple t) {
			return (this.in!=t.in) ? t.in-this.in : t.out-this.out;
		}
	}

	private static Tuple find(int curr, int from) {
		ArrayList<Tuple> children=new ArrayList<>();
		for (int i=0;i<Castles[curr].adjList.size();i++) {
			int n=Castles[curr].adjList.get(i);
			if (n==from) continue;
			children.add(find(n,curr));
		}
		Collections.sort(children); // Highest soldier in, then highest soldier out first.
		Tuple ret=new Tuple(Castles[curr].in,Castles[curr].out);
		for (int i=0;i<children.size();i++) {
			Tuple child=children.get(i);
			ret.in=Math.max(ret.in,ret.out+child.in);
			ret.out+=child.out;
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);
			Castles=new Castle[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Castles[n]=new Castle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			for (int n=0;n<N-1;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				Castles[n1].adjList.add(n2);
				Castles[n2].adjList.add(n1);
			}

			Ans=ANS_MAX;
			for (int n=0;n<N;n++) Ans=Math.min(Ans,find(n,-1).in);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(Ans);
			System.out.println(sb.toString());
		}
	}

}