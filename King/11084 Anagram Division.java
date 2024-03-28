import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static int D;

	private static int count(long curr, int visited, int rem) {
		if (rem==0) return curr%D==0?1:0;

		int ans=0;
		int last=-1;
		for (int i=0;i<Nums.length;i++) if ((visited&(1<<i))==0) {
			if (i>0 && Nums[i]==last) continue;
			long next=curr*10+Nums[i];
			last=Nums[i];
			ans+=count(next,visited|(1<<i),rem-1);
		}
		return ans;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			D=Integer.parseInt(st.nextToken());

			Nums=new int [s.length()];
			for (int i=0;i<s.length();i++) Nums[i]=s.charAt(i)-'0';
			Arrays.sort(Nums);

			System.out.println(count(0,0,Nums.length));
		}
	}
}
