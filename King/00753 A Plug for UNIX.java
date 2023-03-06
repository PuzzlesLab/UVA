import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;

	private static class Tuple {
		int n1, n2;
		
		public Tuple(int n1, int n2) {
			this.n1=n1;
			this.n2=n2;
		}
	}

	private static boolean mcbm(int l) {
		if (Visited[l]) return false;

		Visited[l]=true;
		for (int i=0;i<AdjList[l].size();i++) {
			int r=AdjList[l].get(i);

			if (Pair[r]==-1 || mcbm(Pair[r])) {
				Pair[r]=l;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); // empty
			
			int N=Integer.parseInt(br.readLine());
			HashMap<String,Integer> nameIdxMap=new HashMap<>();
			String [] wallSockets=new String [N];
			for (int n=0;n<N;n++) {
				wallSockets[n]=br.readLine();
				if (!nameIdxMap.containsKey(wallSockets[n])) nameIdxMap.put(wallSockets[n],nameIdxMap.size());
			}

			int M=Integer.parseInt(br.readLine());
			String [] devicePlugs=new String [M];
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				st.nextToken(); // device name is useless.
				devicePlugs[m]=st.nextToken();
				if (!nameIdxMap.containsKey(devicePlugs[m])) nameIdxMap.put(devicePlugs[m],nameIdxMap.size());
			}
			
			int K=Integer.parseInt(br.readLine());
			ArrayList<Tuple> edges=new ArrayList<>();
			for (int k=0;k<K;k++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String s1=st.nextToken();
				if (!nameIdxMap.containsKey(s1)) nameIdxMap.put(s1,nameIdxMap.size());
				String s2=st.nextToken();
				if (!nameIdxMap.containsKey(s2)) nameIdxMap.put(s2,nameIdxMap.size());
				edges.add(new Tuple(nameIdxMap.get(s1),nameIdxMap.get(s2)));
			}

			int types=nameIdxMap.size();
			boolean [][] adapt=new boolean [types][types];
			for (int i=0;i<types;i++) adapt[i][i]=true; // Match self.
			for (int i=0;i<edges.size();i++) {
				Tuple edge=edges.get(i);
				adapt[edge.n1][edge.n2]=true;
			}

			// Do transitive closure
			for (int k=0;k<types;k++) {
				for (int i=0;i<types;i++) {
					for (int j=0;j<types;j++) {
						adapt[i][j]|=adapt[i][k] && adapt[k][j];
					}
				}
			}

			AdjList=new ArrayList [M];
			for (int m=0;m<M;m++) {
				AdjList[m]=new ArrayList<>();
				for (int n=0;n<N;n++) if (adapt[nameIdxMap.get(devicePlugs[m])][nameIdxMap.get(wallSockets[n])]) {
					AdjList[m].add(n);
				}
			}
			
			Pair=new int [N];
			Arrays.fill(Pair,-1);
			int pluggable=0;
			for (int m=0;m<M;m++) {
				Visited=new boolean [M];
				if (mcbm(m)) pluggable++;
			}

			if (testCase>0) System.out.println();
			System.out.println(M-pluggable);
		}
	}

}