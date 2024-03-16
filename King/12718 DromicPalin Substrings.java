import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			String s=br.readLine();
			int ans=0;
			for (int i=0;i<s.length();i++) {
				int [] count=new int [128];
				int odd=0;
				for (int i2=i;i2<s.length();i2++) {
					char c=s.charAt(i2);
					count[c]++;
					odd+=((count[c]&1)==1)?1:-1;
					if (odd<2) ans++;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}
}