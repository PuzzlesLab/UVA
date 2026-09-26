import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] PairR;
	private static int [] PairC;
	
	private static int mcbm(int curr) {
		if (Visited[curr]) return 0;
		
		Visited[curr]=true;
		for (int i=0;i<AdjList[curr].size();i++) {
			int next=AdjList[curr].get(i);
			if (PairC[next]==-1 || mcbm(PairC[next])==1) {
				PairR[curr]=next;
				PairC[next]=curr;
				return 1;
			}
		}
		return 0;
	}

	private static void trace(int r, boolean [] visitedR, boolean [] visitedC) {
		if (visitedR[r]) return;
		
		visitedR[r]=true;
		for (int i=0;i<AdjList[r].size();i++) {
			int c=AdjList[r].get(i);
			if (PairC[c]==r) continue;
			if (visitedC[c]) continue;
			
			visitedC[c]=true;
			if (PairC[c]!=-1) trace(PairC[c],visitedR,visitedC);
		}
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0 0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	int R=Integer.parseInt(st.nextToken());
        	int C=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken());

        	AdjList=new ArrayList[R];
        	for (int r=0;r<R;r++) AdjList[r]=new ArrayList<>();
        	int [] cExists=new int[C];
        	for (int n=0;n<N;n++) {
        		st=new StringTokenizer(br.readLine());
        		int r=Integer.parseInt(st.nextToken())-1;
        		int c=Integer.parseInt(st.nextToken())-1;
        		AdjList[r].add(c);
        		cExists[c]++;
        	}

        	PairR=new int [R];
        	Arrays.fill(PairR,-1);
        	PairC=new int [C];
        	Arrays.fill(PairC,-1);
        	int count=0;
        	for (int r=0;r<R;r++) {
        		Visited=new boolean [R];
        		count+=mcbm(r);
        	}
        	boolean [] visitedR=new boolean [R];
        	boolean [] visitedC=new boolean [C];
        	for (int r=0;r<R;r++) if (PairR[r]==-1) trace(r,visitedR,visitedC);

        	StringBuilder sb=new StringBuilder();
        	sb.append(count);
        	for (int r=0;r<R;r++) if (!visitedR[r]) {
        		sb.append(" r");
        		sb.append(r+1);
        	}
        	for (int c=0;c<C;c++) if (visitedC[c]) {
        		sb.append(" c");
        		sb.append(c+1);
        	}

        	System.out.println(sb);
        	br.readLine();
        }
	}

}