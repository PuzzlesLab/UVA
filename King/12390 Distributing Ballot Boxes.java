import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static boolean valid(int [] pop, int popPerBox, int B) {
		int boxNeeded=0;
		for (int n=0;n<pop.length;n++) {
			boxNeeded+=(pop[n]/popPerBox);
			if (pop[n]%popPerBox!=0) boxNeeded++;
		}
		return boxNeeded<=B;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1 -1")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			int [] pop=new int [N];
			int maxPop=0;
			for (int n=0;n<N;n++) {
				pop[n]=Integer.parseInt(br.readLine());
				maxPop=Math.max(maxPop, pop[n]);
			}

			int ans=maxPop;
			int min=1, max=maxPop;
			while (min<=max) {
				int mid=(min+max)/2;
				if (valid(pop,mid,B)) {
					ans=mid;
					max=mid-1;
				} else min=mid+1;
			}
			System.out.println(ans);
			
			br.readLine();
		}
	}

}
