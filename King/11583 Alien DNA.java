import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			BitSet found=new BitSet(26);

			int ans=0;
			for (int i=0;i<N;i++) {
				char [] seq=br.readLine().toCharArray();
				BitSet seqSet=new BitSet();
				for (char c: seq) seqSet.set(c-'a', true);
				
				if (found.intersects(seqSet)) found.and(seqSet);
				else {
					found = seqSet;
					ans++;
				}
			}

			System.out.println(ans-1);
		}
	}

}