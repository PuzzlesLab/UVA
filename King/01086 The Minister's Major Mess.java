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
	private static int B;
	private static int M;

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
	
	private static void removeLastClause(int n1, int n2) {
		AdjList[n1^1].remove(AdjList[n1^1].size()-1);
		AdjList[n2^1].remove(AdjList[n2^1].size()-1);
	}

	private static int getIndex(int b, boolean pos) {
		return (b<<1)+(pos?0:1);
	}

	private static boolean compute() {
		DfsNum=new int [AdjList.length];
		DfsLow=new int [AdjList.length];
		Visited=new boolean [AdjList.length];
		SCCId=new int [AdjList.length];
		DfsMax=1;
		SCC=0;
		Stack=new Stack<>();
		for (int p=0;p<AdjList.length;p++) if (DfsNum[p]==0) scc(p);

		boolean hasSol=true;
		for (int i=0;i<SCCId.length && hasSol;i+=2) hasSol&=(SCCId[i]!=SCCId[i+1]);
		return hasSol;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        int tc=1;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	B=Integer.parseInt(st.nextToken());
        	M=Integer.parseInt(st.nextToken());

        	AdjList=new ArrayList [B<<1];
        	for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();

        	for (int m=0;m<M;m++) {
        		st=new StringTokenizer(br.readLine());
        		int K=Integer.parseInt(st.nextToken());
        		int [] nIds=new int [K];
        		for (int k=0;k<K;k++) nIds[k]=getIndex(Integer.parseInt(st.nextToken())-1,st.nextToken().charAt(0)=='y');

        		if (K<=2) for (int k=0;k<K;k++) addClause(nIds[k],nIds[k]);
        		else for (int k=0;k<K;k++) for (int k2=k+1;k2<K;k2++) addClause(nIds[k],nIds[k2]);
        	}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			if (!compute()) sb.append("impossible");
			else {
				char [] sol=new char [B];
				for (int i=0;i<SCCId.length;i+=2) {
					if (SCCId[i]<SCCId[i+1]) sol[i>>1]='y';
					else sol[i>>1]='n';
				}

				for (int b=0;b<B;b++) {
					int n1=b<<1; // y
					int n2=n1+1; // n
					
					addClause(n1,n1); // Force b=y, test
					boolean yOK=compute();
					removeLastClause(n1,n1);

					addClause(n2,n2); // Force b=n, test
					boolean nOK=compute();
					removeLastClause(n2,n2);
					
					if (yOK^nOK) sb.append(yOK?'y':'n');
					else sb.append('?');
				}
			}

			System.out.println(sb);
        }
	}

}