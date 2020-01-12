import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class uva {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=0;
		while ((s=br.readLine())!=null) {
			int E=Integer.parseInt(s);
			
			ArrayList<Integer> [] adjList=new ArrayList [E];
			for (int e=0;e<E;e++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				adjList[e]=new ArrayList<>();
				int N=Integer.parseInt(st.nextToken());
				for (int n=0;n<N;n++) adjList[e].add(Integer.parseInt(st.nextToken()));
			}
			
			int [] ways=new int [E];
			ways[0]=1;
			
			/*
			 * Assumption : Outgoing edge of a node always go to node with higher event no.
			 */
			int ans=0;
			for (int e=0;e<E;e++) {
				if (adjList[e].size()==0) ans+=ways[e];
				for (int n : adjList[e]) ways[n]+=ways[e];
			}

			if (testCase>0) System.out.println();
			System.out.println(ans);
			
			testCase++;
			br.readLine();//empty
		}
	}

}