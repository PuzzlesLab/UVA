import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main { 

	private static class Choice{
		int quantity;
		double price;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double unitPrice=Double.parseDouble(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Choice [] choices=new Choice[M];
			for (int m=0;m<M;m++) {
				choices[m]=new Choice();
				
				st=new StringTokenizer(br.readLine());
				choices[m].quantity=Integer.parseInt(st.nextToken());
				choices[m].price=Double.parseDouble(st.nextToken());
			}
			
			double [] dp=new double [101];
			for (int i=1;i<dp.length;i++) dp[i]=i*unitPrice;
			
			for (int i=0;i<choices.length;i++) {
				Choice curr=choices[i];
				for (int k=0;k<dp.length;k++) {
					dp[k]=Math.min(dp[k],curr.price+((k>=curr.quantity)?dp[k-curr.quantity]:0));
				}
			}
			
			st=new StringTokenizer(br.readLine());
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(":\n");
			while (st.hasMoreTokens()) {
				int K=Integer.parseInt(st.nextToken());
				sb.append("Buy ");
				sb.append(K);
				sb.append(" for $");
				sb.append(String.format("%.2f",dp[K]));
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}

}
