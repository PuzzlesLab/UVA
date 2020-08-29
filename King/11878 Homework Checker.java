import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int ans=0;
		
		String addPattern = "(\\d+)\\+(\\d+)=(\\d+)";
		String subtractPattern= "(\\d+)\\-(\\d+)=(\\d+)";
		
		while ((s=br.readLine())!=null) {
			if (s.matches(addPattern)) {
				StringTokenizer st=new StringTokenizer(s,"+");
				int a=Integer.parseInt(st.nextToken());
				st=new StringTokenizer(st.nextToken(),"=");
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				if (a+b==c) ans++; 
			} else if (s.matches(subtractPattern)) {
				StringTokenizer st=new StringTokenizer(s,"-");
				int a=Integer.parseInt(st.nextToken());
				st=new StringTokenizer(st.nextToken(),"=");
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				if (a-b==c) ans++;
			}
		}
		System.out.println(ans);
	}
}