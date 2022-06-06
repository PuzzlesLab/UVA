import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static boolean [] Positive;
	private static boolean [] Negative;
	private static int Final;
	private static HashMap<String, Boolean> Dp;
	
	private static String constructKey(int n, int sum) {
		StringBuilder sb=new StringBuilder();
		sb.append(n);
		sb.append(' ');
		sb.append(sum);
		return sb.toString();
	}

	private static boolean find(int n, int sum) {
		if (n==Nums.length) return sum==Final;

		String key=constructKey(n,sum);
		if (!Dp.containsKey(key)) {
			boolean flag=false;
			if (find(n+1,sum+Nums[n])) {
				flag=true;
				Positive[n]=true;
			}
			if (find(n+1,sum-Nums[n])) {
				flag=true;
				Negative[n]=true;
			}
			Dp.put(key,flag);
		}
		
		return Dp.get(key);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			Final=Integer.parseInt(st.nextToken());
			
			Nums=new int [N];
			for (int i=0;i<Nums.length;i++) Nums[i]=Integer.parseInt(br.readLine());
			
			Positive=new boolean [N];
			Negative=new boolean [N];
			Dp=new HashMap<>();
			boolean found=find(0,0);

			if (!found) System.out.println('*');
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<Nums.length;i++) {
					if (Positive[i]^Negative[i]) sb.append(Positive[i]?'+':'-');
					else sb.append('?');
				}
				System.out.println(sb.toString());
			}
		}
	}

}
