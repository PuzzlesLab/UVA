import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static boolean [] NotPrime=new boolean [300];
	private static ArrayList<Integer> PrimeLexi;
	private static int N;
	private static int [] Selection;

	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		PrimeLexi=new ArrayList<>();
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) PrimeLexi.add(i);
		Collections.sort(PrimeLexi,(x,y)-> String.valueOf(x).compareTo(String.valueOf(y)));
	}

	private static boolean find(int currSum, int pIdx, int sIdx) {
		if (sIdx==Selection.length) return currSum==N;
		if (pIdx==PrimeLexi.size() || currSum>N) return false; // We can't proceed further.

		int pF=PrimeLexi.get(pIdx);

		if (pF!=2 && sIdx+2<=Selection.length && currSum+pF+pF<=N) { // Pick 2.
			Selection[sIdx]=pF;
			Selection[sIdx+1]=pF;
			if (find(currSum+pF+pF,pIdx+1,sIdx+2)) return true;
		}
		
		if (currSum+pF<=N) { // Pick 1
			Selection[sIdx]=pF;
			if(find(currSum+pF,pIdx+1,sIdx+1)) return true;
		}

		// Don't pick.
		if (PrimeLexi.size()-pIdx>=Selection.length-sIdx) if (find(currSum,pIdx+1,sIdx)) return true;
		return false;
	}

	public static void main(String [] args) throws Exception {
		eSieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());

			Selection=new int [T];
			boolean flag=find(0,0,0);

			StringBuilder sb=new StringBuilder();
			sb.append("CASE ");
			sb.append(tc++);
			sb.append(":\n");
			if (flag) {
				for (int i=0;i<Selection.length;i++) {
					sb.append(Selection[i]);
					sb.append('+');
				}
				sb.setLength(sb.length()-1);
			} else sb.append("No Solution.");
			System.out.println(sb.toString());
		}
	}

}