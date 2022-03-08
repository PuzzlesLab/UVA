import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int setBitCountOnSwappable(long num, boolean [] canSwap) {
		int ans=0;
		int pos=0;
		while (num>0) {
			if (canSwap[pos] && num%2==1) ans++;
			pos++;
			num/=2;
		}
		return ans;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			long num=Long.parseLong(s);
			int K=Integer.parseInt(br.readLine());
			StringTokenizer st=new StringTokenizer(br.readLine());
			int [] pos=new int [K];
			boolean [] canSwap=new boolean [62];
			for (int k=0;k<K;k++) {
				pos[k]=Integer.parseInt(st.nextToken());
				canSwap[pos[k]]=true;
			}

			long ans=Long.MIN_VALUE;
			int numBitCount=setBitCountOnSwappable(num,canSwap);
			long max=(1L<<K)-1;
			for (long test=max;test>=0;test--) { // Generate binary number with length swappable count and search
				int currBitCount=Long.bitCount(test);
				if (currBitCount!=numBitCount) continue; // Skip if current binary number doesn't have number of ones to put.

				long tempNum=num;
				long tempTest=test;
				for (int k=K-1;k>=0;k--) {
					// Fill in the binary number to the swappable location, i.e. 11100 into 1[X]1[X]0[X][X][X]
					//                                                                        1   1   1  0  0
					boolean isBitSet=(tempNum&(1L<<pos[k]))!=0;
					boolean toSet=tempTest%2==1;
					tempTest/=2;
					if (isBitSet!=toSet) tempNum^=(1L<<pos[k]);
				}
				if (tempNum%7==0) ans=Math.max(ans,tempNum);
			}
			System.out.println(ans);
		}
	}

}
