import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int S=Integer.parseInt(br.readLine());
		for (int s=1;s<=S;s++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			boolean flag=true;
			for (int i=0;i<13;i++) flag&=Integer.parseInt(st.nextToken())>0;
			System.out.printf("Set #%d: %s\n",s,flag?"Yes":"No");
		}
	}

}