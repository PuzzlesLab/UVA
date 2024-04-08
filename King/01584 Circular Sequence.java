import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			char [] c=br.readLine().toCharArray();
			String smallest="Z";
			for (int i=0;i<c.length;i++) {
				StringBuilder sb=new StringBuilder();
				for (int i2=0;i2<c.length;i2++) sb.append(c[(i+i2)%c.length]);
				String s=sb.toString();
				if (s.compareTo(smallest)<0) smallest=s;
			}
			System.out.println(smallest);
		}
	}
}
