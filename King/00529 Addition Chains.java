import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Main {

	private static int [] solution=null;

	private static void find(ArrayList<int []> chains, ArrayList<int []> nextChains, int max, int maxDepth) {
		for (int [] chain: chains) {
			for (int i=chain.length-1;i>=0;i--) {
				for (int i2=0;i2<=i;i2++) {
					int sum=chain[i]+chain[i2];

					if (sum<=chain[chain.length-1]) continue;	
					if (sum>max) break;
					if (sum<<(maxDepth-chain.length)<max) continue;

					int [] nextChain=Arrays.copyOf(chain, chain.length+1);
					nextChain[chain.length]=sum;
					if (sum==max) {
						solution=nextChain;
						return;
					} else nextChains.add(nextChain);
				}
			}
		}
		
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			int N=sc.nextInt();
			if (N==0) break;

			solution=null;
			if (N>1) {
				ArrayList<int []> chains=new ArrayList<>();
				chains.add(new int [] {1});
				int maxDepth=(int)Math.ceil(Math.log(N)/Math.log(2))+2;
				while (solution==null) {
					if (chains.size()==0) break;
					ArrayList<int []> nextChains=new ArrayList<>();
					find(chains, nextChains,N,maxDepth);
					chains=nextChains;
				}
			} else solution=new int [] {1};

			StringBuilder sb=new StringBuilder();
			for (int num: solution) {
				sb.append(num);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
