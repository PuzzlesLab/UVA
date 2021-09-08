import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	private static ArrayList<Integer> primeNumbers=new ArrayList<>();

	private static void sieveOfEratothenes() {
		boolean [] notPrime=new boolean[35000];
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) {
			for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		}
		
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primeNumbers.add(i);
	}

	public static void main (String [] args) throws Exception {
		sieveOfEratothenes();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int [] dp=new int [3502];
		dp[1]=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);

			if (dp[N]==0) {
				ArrayList<Integer> people=new ArrayList<>();
				for (int n=1;n<=N;n++) people.add(n);

				int currPos=0;
				for (int n=0;people.size()>1;n++) {
					currPos=(currPos+primeNumbers.get(n)-1)%people.size();
					people.remove(currPos);
				}
				
				dp[N]=people.get(0);
			}

			System.out.println(dp[N]);
		}
	}

}