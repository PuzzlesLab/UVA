import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			long S=Integer.parseInt(st.nextToken());
			long D=Long.parseLong(st.nextToken());
			
			long nextS=S;
			long day=0;
			while (day<D) {
				day+=nextS;
				nextS+=1;
			}
			System.out.println(nextS-1);
		}
	}

}