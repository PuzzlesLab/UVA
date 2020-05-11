import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int P=Integer.parseInt(br.readLine());
			int N=Integer.parseInt(br.readLine());
			
			int [] leds=new int [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int p=0;p<P;p++) leds[n]=(leds[n] << 1)+Integer.parseInt(st.nextToken());
			}
			
			int max=(int)(Math.pow(2, P));
			int ans=Integer.MAX_VALUE;
			for (int i=0;i<max;i++) if (Integer.bitCount(i)<ans) {
				HashSet<Integer> symbols=new HashSet<>();
				for (int n=0;n<N;n++) {
					int newSymbol=leds[n] & i; //AND the symbol. 0=LED not working, 1=LED working.
					if (symbols.contains(newSymbol)) break; //Same symbol detected, invalid mask!
					else symbols.add(newSymbol);
				}
				if (symbols.size() == N) ans=Integer.bitCount(i);
			}
			System.out.println(ans);
		}
	}

}