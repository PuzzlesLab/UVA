import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	private static boolean isCool(char [] ch) {
		HashSet<Character> chSet=new HashSet<>();
		int [] cCount=new int [128];
		for (char c: ch) {
			chSet.add(c);
			cCount[c]++;
		}
		if (chSet.size()<2) return false;
		
		HashSet<Integer> countSet=new HashSet<>();
		for (char c : chSet) {
			if (countSet.contains(cCount[c])) return false;
			else countSet.add(cCount[c]);
		}
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int ans=0;
			for (int n=0;n<N;n++) ans+=(isCool(br.readLine().toCharArray())) ? 1 : 0;
			System.out.printf("Case %d: %d\n",testCase++,ans);
		}
	}
}