import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine(),"=");
			int xCoeff=0, rhs=0;
			
			String left=st.nextToken()+"+";
			int currCoeff=0, currCoeffDigit=0;
			boolean isX=false, isNeg=false;
			for (char c : left.toCharArray()) {
				if (c=='x') isX=true;
				else if (Character.isDigit(c)) {
					currCoeff=currCoeff*10+(c-'0');
					currCoeffDigit++;
				}
				else if (c=='+') {
					if (isX && currCoeffDigit==0) currCoeff=1;
					if (isNeg) currCoeff=-currCoeff;
					if (isX) xCoeff+=currCoeff;
					else rhs-=currCoeff;
					isNeg=false;
					isX=false;
					currCoeff=0;
					currCoeffDigit=0;
				} else if (c=='-') {
					if (isX && currCoeffDigit==0) currCoeff=1;
					if (isNeg) currCoeff=-currCoeff;
					if (isX) xCoeff+=currCoeff;
					else rhs-=currCoeff;
					isNeg=true;
					isX=false;
					currCoeff=0;
					currCoeffDigit=0;
				}
			}
			
			String right=st.nextToken()+"+";
			currCoeff=0; currCoeffDigit=0; isX=false; isNeg=false;
			for (char c : right.toCharArray()) {
				if (c=='x') isX=true;
				else if (Character.isDigit(c)) {
					currCoeff=currCoeff*10+(c-'0');
					currCoeffDigit++;
				} else if (c=='+') {
					if (isX && currCoeffDigit==0) currCoeff=1;
					if (isNeg) currCoeff=-currCoeff;
					if (isX) {
						xCoeff-=currCoeff;
					} else rhs+=currCoeff;
					isNeg=false;
					isX=false;
					currCoeff=0;
					currCoeffDigit=0;
				} else if (c=='-') {
					if (isX && currCoeffDigit==0) currCoeff=1;
					if (isNeg) currCoeff=-currCoeff;
					if (isX) xCoeff-=currCoeff;
					else rhs+=currCoeff;
					isNeg=true;
					isX=false;
					currCoeff=0;
					currCoeffDigit=0;
				}
			}

			if (xCoeff!=0 && rhs==0) System.out.println("0");
			else if (rhs==0) System.out.println("IDENTITY");
			else if (xCoeff==0) System.out.println("IMPOSSIBLE");
			else System.out.println((int)Math.floor((rhs+0.0)/xCoeff));
		}
		
	}
}