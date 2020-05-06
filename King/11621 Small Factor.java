import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		long [] pow2=new long[32]; pow2[0]=1;
		for (int i=1;i<pow2.length;i++) pow2[i]=pow2[i-1]<<1;

		long [] pow3=new long[32]; pow3[0]=1;
		for (int i=1;i<pow3.length;i++) pow3[i]=pow3[i-1]*3;
		
		TreeSet<Long> products=new TreeSet<>();
		for (int i=0;i<pow2.length;i++) for (int i2=0;i2<pow3.length && pow2[i]*pow3[i2]<=2*(long)Integer.MAX_VALUE;i2++) products.add(pow2[i]*pow3[i2]);
		
		while (!(s=br.readLine()).equals("0")) {
			int m=Integer.parseInt(s);
			System.out.println(products.ceiling((long)m));
		}

	}

}