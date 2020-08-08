import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			boolean [] p1=new boolean [5];
			for (int i=0;i<p1.length;i++) p1[i]=Integer.parseInt(st.nextToken())==1;
			
			st=new StringTokenizer(br.readLine());
			boolean [] p2=new boolean [5];
			for (int i=0;i<p1.length;i++) p2[i]=Integer.parseInt(st.nextToken())==1;
			
			boolean conflict=false;
			for (int i=0;i<p1.length;i++) if (p1[i]==p2[i]) {
				conflict=true;
				break;
			}
			
			System.out.println(conflict ? 'N' : 'Y');
		}

	}

}