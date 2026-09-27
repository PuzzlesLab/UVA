import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;

	private static int mcbm(int n) {
		if (Visited[n]) return 0;
		
		Visited[n]=true;
		for (int i=0;i<AdjList[n].size();i++) {
			int next=AdjList[n].get(i);
			if (Pair[next]==-1 || mcbm(Pair[next])==1) {
				Pair[next]=n;
				return 1;
			}
		}

		return 0;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine().trim());
        for (int tc=0;tc<TC;tc++) {
        	int NI=Integer.parseInt(br.readLine().trim());
        	int NS=Integer.parseInt(br.readLine().trim());
        	
        	AdjList=new ArrayList [NI];
        	for (int i=0;i<NI;i++) AdjList[i]=new ArrayList<>();
        	for (int i=0;i<NS;i++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		int S=Integer.parseInt(st.nextToken())-1;
        		int E=Integer.parseInt(st.nextToken())-1;
        		AdjList[S].add(E);
        	}

        	Pair=new int [NI];
        	Arrays.fill(Pair,-1);

        	int ans=0;
        	for (int i=0;i<NI;i++) {
        		Visited=new boolean[NI];
        		ans+=mcbm(i);
        	}

        	System.out.println(NI-ans);
        }
	}

}