import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			String s=br.readLine();
			char [] x, y, z;
			boolean flag=false;
			if (s.matches("(\\?)*M(\\?)*E(\\?)*")) {
				try {
					StringTokenizer st=new StringTokenizer(s,"M");
					x=st.nextToken().toCharArray();
					int xQMCount=0;
					for (char c : x) if (c=='?') xQMCount++;
					
					st=new StringTokenizer(st.nextToken(),"E");
					y=st.nextToken().toCharArray();
					int yQMCount=0;
					for (char c : y) if (c=='?') yQMCount++;
					
					z=st.nextToken().toCharArray();
					int zQMCount=0;
					for (char c : z) if (c=='?') zQMCount++;
					
					
					/*
					 * Relation for base case:
					 * y = 1
					 * x = z-1
					 * y != x && y != z
					 * z != x
					 * 
					 * Relation for derived cases:
					 * for (N>1), verify:
					 *   dy=y+N
					 *   dz=z+N
					 *   dx=dz-1
					 */
					
					if (x.length>0 && y.length>0 && z.length>0 && xQMCount==x.length && yQMCount==y.length && zQMCount==z.length && xQMCount<=zQMCount && yQMCount!=zQMCount) {
						if (yQMCount==1) flag=xQMCount==zQMCount-1;
						else flag=(xQMCount==zQMCount-yQMCount);
					}
				} catch (Exception e) {}
			}
			System.out.println(flag ? "theorem" : "no-theorem");
		}
	}
}