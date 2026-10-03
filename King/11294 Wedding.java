import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static int [] DfsNum;
	private static int [] DfsLow;
	private static boolean [] Visited;
	private static int [] SCCId;
	private static int DfsMax;
	private static int SCC;
	private static Stack<Integer> Stack;

	private static void scc(int curr) {
		DfsNum[curr]=DfsMax++;
		DfsLow[curr]=DfsNum[curr];
		Visited[curr]=true;
		Stack.push(curr);

		for (int next: AdjList[curr]) {
			if (DfsNum[next]==0) scc(next);
			if (Visited[next]) DfsLow[curr]=Math.min(DfsLow[curr], DfsLow[next]);
		}

		if (DfsNum[curr]==DfsLow[curr]) {
			SCC++;
			while (true) {
				int n=Stack.pop();
				SCCId[n]=SCC;
				Visited[n]=false;
				if (n==curr) break;
			}
		}
	}
	
	private static void addClause(int n1, int n2) {
		AdjList[n1^1].add(n2);
		AdjList[n2^1].add(n1);
	}

	private static int getIndex(int n, char c) {
		int r=n<<1;
		if (c=='w') r++;
		return r;
	}
	
	private static int getIndex(String s) {
		int n=Integer.parseInt(s.substring(0,s.length()-1));
		char c=s.charAt(s.length()-1);
		return getIndex(n,c);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	int N=Integer.parseInt(st.nextToken());
        	int A=Integer.parseInt(st.nextToken());

        	AdjList=new ArrayList [N<<1];
        	for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();

        	AdjList[getIndex(0,'h')].add(getIndex(0,'w'));
        	for (int i=0;i<A;i++) {
        		st=new StringTokenizer(br.readLine());
        		addClause(getIndex(st.nextToken()),getIndex(st.nextToken()));
        	}
        	
			DfsNum=new int [AdjList.length];
			DfsLow=new int [AdjList.length];
			Visited=new boolean [AdjList.length];
			SCCId=new int [AdjList.length];
			DfsMax=1;
			SCC=0;
			Stack=new Stack<>();
			for (int p=0;p<AdjList.length;p++) if (DfsNum[p]==0) scc(p);

			boolean bad=false;
			for (int i=0;i<N;i++) {
				if (SCCId[i<<1]==SCCId[(i<<1)+1]) {
					bad=true;
					break;
				}
			}
			
			if (bad) {
				System.out.println("bad luck");
				continue;
			}

			StringBuilder sb=new StringBuilder();
			for (int i=1;i<N;i++) {
				if (SCCId[i<<1]<SCCId[(i<<1)+1]) {
					sb.append(i);
					sb.append('h');
				} else {
					sb.append(i);
					sb.append('w');
				}
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb);
        }
	}

}