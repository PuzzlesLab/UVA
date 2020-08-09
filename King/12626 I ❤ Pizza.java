import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			//AAAMGIRRT
			char [] ch=br.readLine().toCharArray();
			int [] count=new int [128];
			for (char c : ch) count[c]++;
			int [] pizza= {count['A']/3, count['M'], count['G'], count['I'], count['R']/2, count['T']};
			int min=Integer.MAX_VALUE;
			for (int i : pizza) min=Math.min(min,i);
			System.out.println(min);
		}
	}

}