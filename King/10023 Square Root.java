import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class euler2 {
	
	public static void main(String[] args) throws IOException {
		BigInteger bi2=BigInteger.valueOf(2);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			BigInteger target=new BigInteger(br.readLine());
			String ts=target.toString();
			
			//Bisection method
			BigInteger min=BigInteger.ZERO;
			BigInteger max=new BigInteger(ts.substring(0, ts.length()/2+1)); //cut target into half length+1.
			BigInteger mid=BigInteger.ZERO;
			boolean found=false;
			
			while (min.compareTo(max)<=0) {
				mid=min.add(max).divide(bi2);
				BigInteger midPow2=mid.pow(2);
				int cmp=midPow2.compareTo(target);
				if (cmp==0) {
					found=true;
					break;
				} else if (cmp>0) {
					max=mid.subtract(BigInteger.ONE);
				} else {
					min=mid.add(BigInteger.ONE);
				}
			}
			
			if (testCase>0) {
				System.out.println();
			}
			
			if (found) {
				System.out.println(mid);
			} else {
				//Y is not a perfect square. Question doesn't guarantee that.
				//So take the nearest lower value.
				System.out.println(mid.subtract(BigInteger.ONE));
			}
		}
	}
}