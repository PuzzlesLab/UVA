import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long I=Long.parseLong(s);

			long base=(int)((-1+Math.sqrt(1+8*I))/2);
			long baseI=(base*(base+1))/2;

			StringBuilder sb=new StringBuilder();
			if (baseI==I) {
				sb.append(1);
				sb.append('/');
				sb.append(base);
			}
			else {
				long ext=I-baseI-1;
				long num=base+1-ext;
				long dem=1+ext;
				sb.append(num);
				sb.append('/');
				sb.append(dem);
			}
			System.out.println(sb.toString());
		}
	}

}
