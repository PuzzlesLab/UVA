import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Candies;
	private static boolean [][][] Visited;
	private static int Total;
	private static int Ans;

	private static void compute(int n, int aSum, int bSum) {
		if (n==Candies.length) {
    		int cSum=Total-aSum-bSum;
    		Ans=Math.min(Ans,Math.max(aSum,Math.max(bSum,cSum))-Math.min(aSum,Math.min(bSum,cSum)));
    		return;
		}
		if (Visited[n][aSum][bSum]) return;

		Visited[n][aSum][bSum]=true;
		compute(n+1,aSum+Candies[n],bSum);
		compute(n+1,aSum,bSum+Candies[n]);
		compute(n+1,aSum,bSum);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	int N=Integer.parseInt(br.readLine());
        	Candies=new int [N];
        	Total=0;
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for (int n=0;n<N;n++) {
        		Candies[n]=Integer.parseInt(st.nextToken());
        		Total+=Candies[n];
        	}

        	Ans=10000000;
        	Visited=new boolean [N][Total+1][Total+1];
        	compute(0,0,0);

        	System.out.printf("Case %d: %d\n",tc,Ans);
        }
	}

}