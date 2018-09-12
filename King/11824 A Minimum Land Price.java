import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		BigInteger max=new BigInteger("5000000");
		BigInteger two=new BigInteger("2");
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<Integer> list=new ArrayList<>();
			String s;
			
			while (!(s=br.readLine()).equals("0")) list.add(Integer.parseInt(s));
			Collections.sort(list);
			
			BigInteger cost=new BigInteger("0");
			for (int i=list.size()-1;i>=0;i--) {
				cost=cost.add(two.multiply(BigInteger.valueOf(list.get(i)).pow(list.size()-i)));
				if (cost.compareTo(max)>0) break;
			}
			
			if (cost.compareTo(max)<=0) System.out.println(cost);
			else System.out.println("Too expensive");
		}
	}

}
