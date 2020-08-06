import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int H=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			int s1=0, s2=0;
			boolean open=false;
			for (int h=0;h<H;h++) for (char c : br.readLine().toCharArray()) {
				if (c=='/' || c=='\\') {
					s1++;
					open=!open;
				}
				else if (c=='.' && open) s2++;
			}
			System.out.println((s1/2)+s2);
		}
	}

}