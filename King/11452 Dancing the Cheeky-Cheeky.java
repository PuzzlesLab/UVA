import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			String s=br.readLine();
			int len=s.length()>>1;

			while (len>0) {
				int lim=s.length()-1-len;
				boolean flag=true;
				for (int i=s.length()-1;i>lim;i--) {
					flag&=s.charAt(i)==s.charAt(i-len);
					if (!flag) break;
				}
				if (flag) break;
				len--;
			}
			
			String base=s.substring(s.length()-len,s.length());
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<8;i++) sb.append(base.charAt(i%base.length()));
			sb.append("...");
			System.out.println(sb);
		}
	}
}