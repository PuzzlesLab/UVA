import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {
	
	//5 digits is enough.
	public static int countDigits(int d) {
		if (d<10) return 1;
		else if (d<100) return 2;
		else if (d<1000) return 3;
		else if (d<10000) return 4;
		return 5;
	}
	
	public static void main (String [] args) throws Exception {
		TreeSet<Long> startIndexes=new TreeSet<>();
		int max=1;
		long digitCount=0;
		while (digitCount<2147483647) {
			startIndexes.add(digitCount+1);
			for (int i=1;i<=max;i++) digitCount+=countDigits(i);
			max++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			long l=Long.parseLong(br.readLine());
			long lowerStartIndex=startIndexes.contains(l) ? l : startIndexes.floor(l);
			int ans=-1;
			
			for (int i=1;ans==-1;i++) {
				int digits=countDigits(i);
				if (lowerStartIndex+digits>l) {
					int [] nums=new int [digits];
					for (int ni=0;ni<digits;ni++) {
						nums[digits-ni-1]=i%10;
						i/=10;
					}
					ans=nums[(int)(l-lowerStartIndex)];
				} else lowerStartIndex+=digits;
			}
				
			System.out.println(ans);
		}

	}

}