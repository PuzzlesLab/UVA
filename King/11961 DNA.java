import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static final char [] Elements = {'A','C','T','G'};
	private static TreeSet<String> Ans;
	
	private static void dfs(char [] currDna, char [] oriDna, int swapLeft, int currPos) {
		Ans.add(new String(currDna));
		if (currPos<currDna.length) {
			dfs(currDna,oriDna,swapLeft,currPos+1);
			if (swapLeft>0) {
				for (char c: Elements) if (c!=oriDna[currPos]) {
					currDna[currPos]=c;
					dfs(currDna,oriDna,swapLeft-1,currPos+1);
					currDna[currPos]=oriDna[currPos];
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			char [] dna=br.readLine().toCharArray();
			char [] oriDna=Arrays.copyOf(dna, N);

			Ans=new TreeSet<>();
			dfs(dna,oriDna,K,0);
			
			StringBuilder sb=new StringBuilder();
			sb.append(Ans.size());
			sb.append('\n');
			for (String s: Ans) {
				sb.append(s);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
