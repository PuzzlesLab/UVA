import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			int minSec=1;
			int maxSec=Integer.MAX_VALUE-1;
			int ans=maxSec;
			while (maxSec-minSec>1) {
				int midSec=(minSec+maxSec)/2;
				double lapX=midSec/(double)X;
				double lapY=midSec/(double)Y;
				if (lapX>=lapY+1) {
					maxSec=midSec;
					ans=Math.min(ans,midSec);
				} else minSec=midSec;
			}

			System.out.println((int)Math.ceil(ans/(double)X));
		}
	}

}