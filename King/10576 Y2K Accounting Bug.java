import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static long Max=0;
	public static long [] Possibles= {0,0};

	public static void dfs (int currMonth, long [] monthV, long months5V, long totalSum) {
		if (currMonth<=4) {
			for (long p : Possibles) {
				monthV[currMonth]=p;
				if (currMonth<4 || months5V+p<0) dfs(currMonth+1, monthV, months5V+p, totalSum+p);
			}
		} else if (currMonth>=5 && currMonth<monthV.length) {
			for (long p : Possibles) {
				long testValue=months5V-monthV[currMonth-5]+p;
				if (testValue<0) {
					monthV[currMonth]=p;
					dfs(currMonth+1, monthV, testValue, totalSum+p);
				}
			}
		} else Max=Long.max(Max, totalSum);
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Possibles[0]=Long.parseLong(st.nextToken());
			Possibles[1]=-Long.parseLong(st.nextToken());
			Max=Possibles[1]*12;
			
			dfs(0,new long [12],0,0);
			
			System.out.println(Max>=0 ? Max : "Deficit");
		}
	}

}
