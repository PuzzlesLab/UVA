import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	private static int N;
	private static int M;
	private static ArrayList<Integer> [] AdjList;
	private static int [] Match;
	private static boolean [] Visited;

	private static int mcbm(int l) {
		if (Visited[l]) return 0;

		Visited[l]=true;
		for (int i=0;i<AdjList[l].size();i++) {
			int r=AdjList[l].get(i);
			if (Match[r]==-1 || mcbm(Match[r])==1) {
				Match[r]=l;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        while (true) {
        	N=sc.nextInt();
        	if (N==0) break;
        	M=sc.nextInt();
        	int K=sc.nextInt();
        	
        	AdjList=new ArrayList [N];
        	for (int n=0;n<AdjList.length;n++) AdjList[n]=new ArrayList<>();
        	
        	for (int k=0;k<K;k++) {
        		sc.nextInt(); // ID is not important.
        		int l=sc.nextInt();
        		int r=sc.nextInt();
        		if (l>0 && r>0) AdjList[l].add(r);
        	}

        	Match=new int [M];
        	Arrays.fill(Match,-1);
        	int ans=0;
        	for (int l=1;l<N;l++) {
        		Visited=new boolean [N];
        		ans+=mcbm(l);
        	}

        	System.out.println(ans);
        }
	}

}