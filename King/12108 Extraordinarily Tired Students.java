import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] A=new int [N];
			int [] B=new int [N];
			int [] currPos=new int [N];
			boolean [] awake=new boolean[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				A[n]=Integer.parseInt(st.nextToken());
				B[n]=Integer.parseInt(st.nextToken());
				
				int c=Integer.parseInt(st.nextToken());
				if (c<=A[n]) {
					awake[n]=true;
					currPos[n]=c;
				} else currPos[n]=c-A[n];
			}
			
			HashSet<String> visitedStates=new HashSet<>();
			int min=1;
			while (true) {
				//System.out.println(min+" - "+Arrays.toString(currPos));
				//System.out.println(min+" - "+Arrays.toString(awake));
				boolean allAwake=true;
				for (int n=0;n<N;n++) allAwake&=awake[n];
				if (allAwake) break;
				
				boolean [] nextAwake=Arrays.copyOf(awake, N);
				
				int awakeCount=0;
				for (int n=0;n<N;n++) if (awake[n]) awakeCount++;
				int sleepCount=N-awakeCount;

				for (int n=0;n<N;n++) {
					currPos[n]++;
					if (awake[n] && currPos[n]>A[n]) {
						if (sleepCount>awakeCount) nextAwake[n]=false;
						currPos[n]=1;
					} else if (!awake[n] && currPos[n]>B[n]) {
						currPos[n]=1;
						nextAwake[n]=true;
					}
				}
				
				awake=nextAwake;
				StringBuilder sb=new StringBuilder();
				sb.append(Arrays.toString(currPos));
				sb.append(Arrays.toString(awake));
				String state=sb.toString();
				if (visitedStates.contains(state)) {
					min=-1;
					break;
				} else {
					visitedStates.add(state);
					min++;
				}
			}
			
			System.out.printf("Case %d: %d\n", testCase++, min);
		}
	}
}