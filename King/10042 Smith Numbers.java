import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	private static int sumOfDigits (int n) {
		int sum=0;
		int temp=n;
		while (temp>0) {
			sum+=temp%10;
			temp/=10;
		}
		return sum;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		boolean [] notPrime=new boolean [31624]; //SQRT(10^9)
		for (int i=2;i<=Math.sqrt(notPrime.length);i++) for (int i2=i*i;i2<notPrime.length;i2+=i) {
			notPrime[i2]=true;
		}
		ArrayList<Integer> primeNums=new ArrayList<>();
		for (int i=2;i<notPrime.length;i++) if (!notPrime[i]) primeNums.add(i);
		
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int n=Integer.parseInt(br.readLine());
			
			boolean foundSolution=false;
			while (!foundSolution) {
				n++;
				int sumOfDigits=sumOfDigits(n);

				int sumOfPrimeFactors=0;
				int temp=n;
				for (int i=0;i<primeNums.size() && primeNums.get(i)*primeNums.get(i)<=temp;i++) {
					int pf=primeNums.get(i);
					if (temp%pf==0) {
						sumOfPrimeFactors+=sumOfDigits(pf);
						temp/=pf;
						i--;
					}
				}
				sumOfPrimeFactors+=sumOfDigits(temp);
				if (temp==n) sumOfPrimeFactors+=1;
				
				foundSolution = sumOfDigits == sumOfPrimeFactors;
			}
			
			System.out.println(n);
		}
		
	}

}