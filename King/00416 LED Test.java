import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int Segments=7;
	private static String [] Expected={
		"YYYYYYN","NYYNNNN","YYNYYNY","YYYYNNY","NYYNNYY",
		"YNYYNYY","YNYYYYY","YYYNNNN","YYYYYYY","YYYYNYY"};
	private static char [][] Actual;

	private static boolean find(boolean [] broken, int currLevel, int nextStart) {
		if (currLevel==Actual.length) return true;
		
		for (int i=0;i<Segments;i++) {
			if (broken[i] && Actual[currLevel][i]=='Y') return false;
			else if (Expected[nextStart].charAt(i)=='N' && Actual[currLevel][i]=='Y') return false;
			else if (Expected[nextStart].charAt(i)=='Y' && Actual[currLevel][i]=='N') broken[i]=true;
		}
		
		return find(broken,currLevel+1,nextStart-1);
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N==0) break;
			Actual=new char [N][];
			for (int n=0;n<N;n++) Actual[n]=br.readLine().toCharArray();

			boolean flag=false;
			for (int start=9;start>=N-1 && !flag;start--) { // Decrease in consecutive way
				flag=find(new boolean[Segments],0,start);
			}

			System.out.println(flag?"MATCH":"MISMATCH");
		}
	}

}
