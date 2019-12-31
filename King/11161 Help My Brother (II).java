import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] ans=new BigInteger [1501];
		ans[1]=new BigInteger("0");
		ans[2]=new BigInteger("1");
		ans[3]=new BigInteger("5");
		ans[4]=new BigInteger("9");
		
		BigInteger [] width=new BigInteger [1501];
		width[3]=new BigInteger("2");
		width[4]=new BigInteger("3");
		
		BigInteger counter=new BigInteger("7");
		for (int i=5;i<ans.length;i++) {
			width[i]=width[i-1].add(width[i-2]);
			BigInteger min=counter;
			BigInteger max=counter.add(width[i]).subtract(BigInteger.ONE);
			ans[i]=min.add(max).divide(BigInteger.valueOf(2));
			counter=counter.add(width[i]);
		}
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=0;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			System.out.printf("Set %d:\n%s\n", ++t, ans[N]);
		}
	}

}