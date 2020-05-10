import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static class Solution implements Comparable<Solution> {
		double sum, ad, bd, cd;
		public Solution (double ad, double bd, double cd, double sum) {
			this.ad=ad;
			this.bd=bd;
			this.cd=cd;
			this.sum=sum;
		}
		public int compareTo(Solution s) {
			if (this.sum>s.sum) return 1;
			else if (this.sum==s.sum) return 0;
			return -1;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int min=(int)(Double.parseDouble(st.nextToken())*100);
			int max=(int)(Double.parseDouble(st.nextToken())*100);
			
			ArrayList<Solution> solutions=new ArrayList<>();
			for (int a=1;a<max;a++) {
				double ad=a/100.0;
				for (int b=a;a+b<max;b++) {
					double bd=b/100.0;
					if (a*b > 10000) {
						int c = (10000*(a+b)) / (a*b - 10000);
						int sumCents=a+b+c;
						if (c>=b && sumCents>=min && sumCents<=max) {
							double cd=c/100.0;
							double multiply=ad*bd*cd;
							double sum=ad+bd+cd;
							if (Math.abs(multiply-sum)<1e-7) solutions.add(new Solution(ad,bd,cd,sum));
						}
					}
				}
			}
			Collections.sort(solutions);
			
			StringBuilder sb=new StringBuilder();
			for (Solution sol : solutions) sb.append(String.format("%.2f = %.2f + %.2f + %.2f = %.2f * %.2f * %.2f\n",sol.sum,sol.ad,sol.bd,sol.cd,sol.ad,sol.bd,sol.cd));
			System.out.print(sb.toString());
		}
	}

}