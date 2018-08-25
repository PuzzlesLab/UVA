import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static boolean isRun(int i) {
		int [] num = new int [(int)(Math.log10((double)i) + 1)];
		int c=0;
		boolean [] numExist=new boolean [10];
		while (i>0) {
			if (numExist[i%10]) return false;
			numExist[i%10]=true;
			num[num.length-1-(c++)]=i%10;
			i/=10;
		}
		boolean [] flags=new boolean [num.length];
		int currIndex=0;
		flags[0]=true;
		c=1;
		while (c<=num.length) {
			currIndex=(currIndex+num[currIndex])%num.length;
			if (!flags[currIndex]) flags[currIndex]=true;
			else break;
			c++;
		}
		return currIndex==0 && c==num.length;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		for (int testCase=1; !(s=br.readLine()).equals("0"); testCase++) {
			int n=Integer.parseInt(s);
			while (!isRun(++n)) {}
			System.out.println("Case "+testCase+": "+n);
		}
	}

}
