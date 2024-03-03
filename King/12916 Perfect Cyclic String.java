import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			String s=br.readLine();
			
			int ans=0;
			for (int len=1;len<=s.length() && ans==0;len++) {
				if (s.length()%len!=0) continue;

				boolean flag=true;
				for (int i=0;i<len && flag;i++) {
					char c=s.charAt(i);
					for (int i2=i+len;i2<s.length() && flag;i2+=len) flag &=c==s.charAt(i2);
				}
				if (flag) {
					ans=len;
					break;
				}
			}
			if (ans==0) ans=s.length();
			System.out.println(ans);
		}
	}
}