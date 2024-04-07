import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			for (int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
			String s2=sb.toString();
			
			int [] back=new int [s2.length()+1];
			int i=0;
			int i2=-1;
			back[0]=-1;
			while (i<s2.length()) {
				while (i2>=0 && s2.charAt(i)!=s2.charAt(i2)) i2=back[i2];
				back[++i]=++i2;
			}

			int start=-1;
			i=0;
			i2=0;
			while (i<s.length()) {
				while (i2>=0 && s.charAt(i)!=s2.charAt(i2)) i2=back[i2];
				i++;
				i2++;
				start=i2;
			}

			sb.setLength(0);
			sb.append(s);
			for (int l=start;l<s2.length();l++) sb.append(s2.charAt(l));
			System.out.println(sb);
		}
	}
}