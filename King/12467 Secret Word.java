import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			String s=br.readLine();
			StringBuilder sb=new StringBuilder();
			for (int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
			String s2=sb.toString();
			
			int [] back=new int [s.length()+1];
			back[0]=-1;
			int i=0;
			int i2=-1;
			while (i<s2.length()) {
				while (i2>=0 && s.charAt(i)!=s.charAt(i2)) i2=back[i2];
				back[++i]=++i2;
			}

			int sol=0;
			i=0;
			i2=0;
			while (i<s2.length()) {
				while (i2>=0 && s2.charAt(i)!=s.charAt(i2)) i2=back[i2];
				i++;
				i2++;
				sol=Math.max(sol,i2);
				if (i2==s2.length()) i2=back[i2];
			}

			sb.setLength(0);
			for (int temp=s2.length()-sol;temp<s2.length();temp++) sb.append(s2.charAt(temp));
			System.out.println(sb);
		}
	}
}