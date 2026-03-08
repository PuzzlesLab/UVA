import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static boolean [] NotPrime=new boolean [10000000];
	private static int [] Digits;
	private static int Ans;
	private static boolean [] NumFlag;
	
	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		NotPrime[0]=true;
		NotPrime[1]=true;
	}

	private static void search(int curr, boolean [] visited) {
		if (NumFlag[curr]) return;

		NumFlag[curr]=true;
		if (!NotPrime[curr]) Ans++;

		for (int i=0;i<visited.length;i++) if (!visited[i]) {
			visited[i]=true;
			search(curr*10+Digits[i],visited);
			visited[i]=false;

		}
	}

	public static void main(String [] args) throws Exception {
		eSieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			char [] cList=br.readLine().toCharArray();
			Digits=new int [cList.length];
			for (int i=0;i<cList.length;i++) Digits[i]=cList[i]-'0';
			Arrays.sort(Digits);

			Ans=0;
			NumFlag=new boolean [NotPrime.length];
			search(0,new boolean [Digits.length]);
			System.out.println(Ans);
		}
	}

}
