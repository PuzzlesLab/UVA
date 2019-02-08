import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		boolean [] notPrime=new boolean [10000001];
		notPrime[0]=true;
		notPrime[1]=true;
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N<8) System.out.println("Impossible.");
			else if (N%2==1) { //Goldbach's conjecture (Every even number is the sum of 2 prime numbers)
				System.out.print("2 3 ");
				int toFind=N-5;
				for (int left=2;left<=toFind/2;left++) if (!notPrime[left] && !notPrime[toFind-left]) {
					System.out.printf("%d %d\n", left, toFind-left);
					break;
				}
			} else {
				System.out.print("2 2 ");
				int toFind=N-4;
				for (int left=2;left<=toFind/2;left++) if (!notPrime[left] && !notPrime[toFind-left]) {
					System.out.printf("%d %d\n", left, toFind-left);
					break;
				}
			}
		}
	}

}