import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static boolean match(char [] d1, char [] d2) {
		boolean flag=true;
		for (int i=0;i<d1.length && flag;i++) flag=d1[i]==d2[i];
		return flag;
	}
	
	public static char[] rotateRight(char [] d) {
		char [] r=new char [6];
		r[0]=d[0];
		r[1]=d[1];
		r[2]=d[3];
		r[3]=d[4];
		r[4]=d[5];
		r[5]=d[2];
		return r;
		
	}
	
	public static char[] rotateBack(char [] d) {
		char [] r=new char [6];
		r[3]=d[3];
		r[5]=d[5];
		r[2]=d[0];
		r[1]=d[2];
		r[4]=d[1];
		r[0]=d[4];
		return r;
	}
	
	public static char[] rotateBottom(char [] d) {
		char [] r=new char [6];
		r[2]=d[2];
		r[4]=d[4];
		r[5]=d[0];
		r[1]=d[5];
		r[3]=d[1];
		r[0]=d[3];
		return r;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			char [] d1=st.nextToken().toCharArray();
			char [] d2=st.nextToken().toCharArray();
			
			boolean found=false;
			for (int i=0;i<4 && !found;i++) {
				d1=rotateRight(d1);
				for (int i2=0;i2<4 && !found;i2++) {
					d1=rotateBack(d1);
					for (int i3=0;i3<4 && !found;i3++) {
						d1=rotateBottom(d1);
						found=match(d1,d2);
					}
				}
			}
			System.out.println(found ? "Equal" : "Not Equal");
		}
	}

}