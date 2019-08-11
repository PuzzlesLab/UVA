import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int bait=0, ans=0, fishCount=0, nextFishCatch=0;
			String s;

			while ((s=br.readLine())!=null) {
				if (s.trim().length()==0) break;
				if (s.equals("fish")) {
					if (bait>=2 && ((nextFishCatch>=7 && fishCount>=2) || ans==0)) {
						nextFishCatch=0;
						fishCount=0;
						bait-=2;
						ans++;
					} else fishCount++;
				} else if (s.equals("bait")) bait=Math.min(bait+1, 6);
				
				if (s.equals("fish") || s.equals("bait") || s.equals("lunch")) nextFishCatch++;
			}
			
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}