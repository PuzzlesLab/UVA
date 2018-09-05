import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int n=Integer.parseInt(s);
			String [] ans=new String [n];
			int currIndex=-1;
			for (int i=0;i<n;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String card=st.nextToken();
				String word=st.nextToken();
				
				for (int i2=0;i2<word.length();i2++) {
					currIndex=(currIndex+1)%n;
					while (ans[currIndex]!=null) currIndex=(currIndex+1)%n;
				}

				ans[currIndex]=card;
			}
			
			StringBuilder sb=new StringBuilder();
			for (int x=0;x<ans.length;x++) {
				sb.append(ans[x]);
				sb.append(" ");
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
