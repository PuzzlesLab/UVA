import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {
	
	public static boolean isNeighbour (int first, int second) {
		return Integer.bitCount(first^second)==1;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			
			int [] weight=new int [(int)Math.pow(2, N)];
			for (int n=0;n<weight.length;n++) weight[n]=Integer.parseInt(br.readLine());
			
			int [] totalWeight=new int [weight.length];
			HashSet<Integer> [] neighbours=new HashSet[weight.length];
			for (int i=0;i<weight.length;i++) neighbours[i]=new HashSet<>();
			
			for (int n=0;n<weight.length;n++) { //Get the sum of weight for neighbours + store the neighbours.
				totalWeight[n]=weight[n];
				neighbours[n].add(n);
				for (int n2=0;n2<N;n2++) {
					int neighbour=n^(1<<n2); //Flip 1 bit at different bit position at a time to get different neighbours.
					totalWeight[n]+=weight[neighbour];
					neighbours[n].add(neighbour);
				}
			}

			int max=0;
			for (int first=0;first<weight.length;first++) for (int second : neighbours[first]) if (second>first) {
				int sum=totalWeight[first]+totalWeight[second]; //Sum up the neighbours weight.
				//Remove the overlapped neighbour weight.
				HashSet<Integer> overlaps=new HashSet<>(neighbours[first]); 
				overlaps.retainAll(neighbours[second]);
				for (int over : overlaps) sum-=weight[over];
				max=Math.max(max, sum);
			}
			System.out.println(max);
		}
	}

}
