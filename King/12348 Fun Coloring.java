import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static boolean [] getColor(int num, int size) {
		boolean [] flag=new boolean[size];
		for (int i=0;num>0;i++) {
			flag[i]=num%2==0;
			num/=2;
		}
		return flag;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine()); // Judge throws RE on this line!
		StringBuilder sb=new StringBuilder();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			HashSet<Integer> [] numSets=new HashSet [M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				numSets[m]=new HashSet<>();
				while (st.hasMoreTokens()) numSets[m].add(Integer.parseInt(st.nextToken()));
			}
			
			boolean ans=false;
			for (int test=0;test<(1<<N) && !ans;test++) {
				boolean [] numColor=getColor(test,N); // Assign color to numbers.
				boolean ok=true;
				for (HashSet<Integer> numSet: numSets) {
					int redCount=0;
					int blueCount=0;
					for (int num: numSet) {
						if (numColor[num-1]) redCount++; // red = true
						else blueCount++;
					}
					if (numSet.size()>1 && (redCount==0 || blueCount==0)) {
						ok=false;
						break;
					}
				}
				ans=ok;
			}
			sb.append(ans ? 'Y' : 'N');
			br.readLine(); //empty
		}
		System.out.print(sb.toString());
	}

}
