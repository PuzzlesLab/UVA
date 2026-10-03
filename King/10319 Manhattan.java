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
	private static int S;
	private static int A;

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

	private static int getSIndex(int s, boolean pos) {
		return (s<<1)+(pos?0:1);
	}

	private static int getAIndex(int a, boolean pos) {
		return getSIndex(a,pos)+(S<<1);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	S=Integer.parseInt(st.nextToken());
        	A=Integer.parseInt(st.nextToken());
        	int M=Integer.parseInt(st.nextToken());

        	AdjList=new ArrayList [(S+A)<<1];
        	for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();

        	for (int m=0;m<M;m++) {
        		st=new StringTokenizer(br.readLine());
        		int s1=getSIndex(Integer.parseInt(st.nextToken())-1,true);
        		int a1=getAIndex(Integer.parseInt(st.nextToken())-1,true);
        		int s2=getSIndex(Integer.parseInt(st.nextToken())-1,true);
        		int a2=getAIndex(Integer.parseInt(st.nextToken())-1,true);
        		
        		if (s1==s2 && a1==a2) continue;
        		boolean sO=s1>s2;
        		boolean aO=a1>a2;
        		
        		if (sO) {
        			a1=a1^1;
        			a2=a2^1;
        		}
        		if (aO) {
        			s1=s1^1;
        			s2=s2^1;
        		}

        		if (s1==s2) addClause(s1,s2);
        		else if (a1==a2) addClause(a1,a2);
        		else {
            		addClause(s1,a1);
            		addClause(s1,s2);
            		addClause(a2,a1);
            		addClause(a2,s2);
        		}
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
			for (int i=0;i<SCCId.length;i+=2) {
				if (SCCId[i]==SCCId[i+1]) {
					bad=true;
					break;
				}
			}

			System.out.println(bad?"No":"Yes");
        }
	}

}