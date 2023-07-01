import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			boolean [] state=new boolean [s.length()];
			for (int i=0;i<state.length;i++) state[i]=s.charAt(i)=='1';

			int zeroNext=0;
			int zeroCount=0;
			for (int i=0;i<state.length;i++) {
				if (state[i]) continue;

				zeroCount++;
				int next=(i+1)%state.length;
				if (!state[next]) zeroNext++;
			}
			/*
			 * zeroNext     zeroCount
			 * --------   > ----------
			 * zeroCount        N
			 * 
			 */
			int a=zeroNext*state.length;
			int b=zeroCount*zeroCount;

			if (a>b) System.out.println("SHOOT");
			else if (a==b) System.out.println("EQUAL");
			else System.out.println("ROTATE");
		}
	}

}
