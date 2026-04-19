import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [] valuesCount=new int [st.countTokens()+1];
			int total=0;
			for (int n=1;n<valuesCount.length;n++) {
				valuesCount[n]=Integer.parseInt(st.nextToken());
				total+=valuesCount[n]*n;
			}

			boolean flag=false;
			if ((total&1)==0) { // is even.
				total>>=1;
				boolean [] reachable=new boolean [total+1];
				reachable[0]=true;
				for (int n=1;n<valuesCount.length && !reachable[reachable.length-1];n++) {
					int temp=valuesCount[n];
					for (int p=1;temp>0;p<<=1) {
						int multi=Math.min(p,temp);
						int subtract=multi*n;
						for (int amount=reachable.length-1;amount>=subtract;amount--) {
							if (reachable[amount-subtract]) reachable[amount]=true;
						}
						temp-=Math.min(p,temp);
					}
				}
				flag=reachable[reachable.length-1];
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Collection #");
			sb.append(tc++);
			sb.append(":\n");
			sb.append(flag?"Can":"Can't");
			sb.append(" be divided.\n");
			System.out.println(sb.toString());
		}
	}

}