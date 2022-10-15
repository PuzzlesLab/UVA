import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static int DFSNumMax;
	private static int [] DFSNum;
	private static int [] DFSLow;
	private static int [] Parent;
	private static int [] Count;
	private static int SCCCount;

	private static boolean scc(int curr) {
		DFSNum[curr]=DFSLow[curr]=DFSNumMax++;
		
		for (int next: AdjList[curr]) {
			if (DFSNum[next]==0) {
				Parent[next]=curr;
				if (!scc(next)) return false; // Ends early.
				DFSLow[curr]=Math.min(DFSLow[curr],DFSLow[next]);
			} else { // Runs into same point! 0 -> 1 -> 2 -> 0, increment count[1] & count[2] by 1.
				DFSLow[curr]=Math.min(DFSLow[curr],DFSLow[next]);
				
				int temp=curr;
				while (Parent[temp]!=next) {
					Count[temp]++;
					if (Count[temp]>=2) return false; // Ends early.
					temp=Parent[temp];
				}
			}
		}
		
		if (DFSNum[curr]==DFSLow[curr]) {
			SCCCount++;
			if (SCCCount>=2) return false; // Ends early.
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			int M=Integer.parseInt(br.readLine());
			
			AdjList=new ArrayList[N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();

			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				AdjList[from].add(to);
			}

			DFSNumMax=1;
			DFSNum=new int [N];
			DFSLow=new int [N];
			Parent=new int [N];
			Count=new int [N];
			SCCCount=0;
			boolean flag=true;
			for (int n=0;n<N && flag;n++) if (DFSNum[n]==0) {
				flag&=scc(n);
			}
			System.out.println(flag?"YES":"NO");
		}
	}

}
