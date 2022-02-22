import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int base=Integer.parseInt(st.nextToken());
			int ff=Integer.parseInt(st.nextToken());
			int sf=Integer.parseInt(st.nextToken());
			
			int x=ff;
			int ans=0;
			while (true) {
				int a = x/base; // first digit
				int b = x%base; // second digit
				x=a+b*sf;
				ans++;
				if (x==ff) break;
			}
			
			System.out.println(ans);
		}
	}

}