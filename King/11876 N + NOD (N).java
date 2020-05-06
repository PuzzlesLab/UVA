import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	private static int nod(int d) {
		if (d==1) return 1;
		else if (d==2) return 2;
		
		int sqrt=(int)(Math.sqrt(d));
		int c=0;
		for (int i=1;i<=sqrt;i++) if (d%i==0) c+=2;
		if (sqrt*sqrt==d) c--;
		return c;
	}
	
	public static void main (String [] args) throws Exception {
		int N=1;
		TreeSet<Integer> seq=new TreeSet<>();
		HashMap<Integer,Integer> index=new HashMap<>();
		int indexMax=0;
		while (N<1000000) {
			seq.add(N);
			index.put(N, indexMax++);
			N+=nod(N);
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int down=Integer.parseInt(st.nextToken());
			int up=Integer.parseInt(st.nextToken());
			
			int size=index.get(seq.floor(up))-index.get(seq.ceiling(down))+1;
			System.out.printf("Case %d: %d\n", testCase, size);
		}
	}

}