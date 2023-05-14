import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int [] coe=new int [st.countTokens()];
			for (int i=0;i<coe.length;i++) coe[i]=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] values=new int [st.countTokens()];
			for (int i=0;i<values.length;i++) values[i]=Integer.parseInt(st.nextToken());
			
			// https://en.wikipedia.org/wiki/Horner%27s_method
			int [] ans=new int [values.length];
			for (int i=0;i<values.length;i++) {
				for (int i2=0;i2<coe.length;i2++) {
					ans[i]*=values[i];
					ans[i]+=coe[i2];
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<ans.length;i++) {
				sb.append(ans[i]);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}