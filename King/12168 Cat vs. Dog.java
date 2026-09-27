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
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	st.nextToken(); // Not important.
        	st.nextToken(); // Not important.
        	int V=Integer.parseInt(st.nextToken());
        	
        	ArrayList<String []> dogVoters=new ArrayList<>();
        	ArrayList<String []> catVoters=new ArrayList<>();
        	for (int v=0;v<V;v++) {
        		st=new StringTokenizer(br.readLine());
        		String [] voter={st.nextToken(),st.nextToken()};
        		if (voter[0].charAt(0)=='D') dogVoters.add(voter);
        		else catVoters.add(voter);
        	}

        	AdjList=new ArrayList [dogVoters.size()];
        	for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();
        	for (int i=0;i<dogVoters.size();i++) {
        		String [] v1=dogVoters.get(i);
        		for (int i2=0;i2<catVoters.size();i2++) {
        			String [] v2=catVoters.get(i2);
        			if (v1[0].equals(v2[1]) || v1[1].equals(v2[0])) {
        				AdjList[i].add(i2);
        			}
        		}
        	}

        	
        	Pair=new int [catVoters.size()];
        	Arrays.fill(Pair,-1);
        	int count=0;
        	for (int i=0;i<dogVoters.size();i++) {
        		Visited=new boolean [dogVoters.size()];
        		count+=mcbm(i);
        	}
        	System.out.println(V-count);
        }
	}

}