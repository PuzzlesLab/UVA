import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [][] coefficients=new BigInteger[51][];
		coefficients[0]=new BigInteger [1];
		coefficients[0][0]=BigInteger.ONE;
		for (int i=1;i<coefficients.length;i++) {
			coefficients[i]=new BigInteger [i+1];
			coefficients[i][0]=BigInteger.ONE;
			for (int j=1;j<coefficients[i-1].length;j++) coefficients[i][j]=coefficients[i-1][j-1].add(coefficients[i-1][j]);
			coefficients[i][i]=BigInteger.ONE;
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine(),"^");
			String expr=st.nextToken();
			int pow=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(expr.substring(1, expr.length()-1),"+");
			String term1=st.nextToken();
			String term2=st.nextToken();
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			for (int p=0;p<=pow;p++) {
				if (!coefficients[pow][p].equals(BigInteger.ONE)) {
					sb.append(coefficients[pow][p]);
					sb.append('*');
				}
				
				int term1Pow=pow-p;
				if (term1Pow==1) {
					sb.append(term1);
					sb.append('*');
				} else if (term1Pow>1) {
					sb.append(term1);
					sb.append('^');
					sb.append(term1Pow);
					sb.append('*');
				}
				
				int term2Pow=p;
				if (term2Pow==1) {
					sb.append(term2);
					sb.append('*');
				} else if (term2Pow>1) {
					sb.append(term2);
					sb.append('^');
					sb.append(term2Pow);
					sb.append('*');
				}
				
				sb.setLength(sb.length()-1);
				sb.append('+');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}