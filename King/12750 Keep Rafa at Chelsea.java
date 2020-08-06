import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int ans=0, noWinCount=0;
			boolean ended=false;
			for (int n=0;n<N;n++) {
				char c=br.readLine().charAt(0);
				if (!ended) {
					if (c == 'W') {
						ans++;
						noWinCount=0;
					} else {
						noWinCount++;
						ans++;
						if (noWinCount>=3) ended=true;
					}
				}

			}
			System.out.printf("Case %d: %s\n", testCase, ended ? ans : "Yay! Mighty Rafa persists!");
		}
	}

}