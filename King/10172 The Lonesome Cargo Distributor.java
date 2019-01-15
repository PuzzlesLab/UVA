import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int carrierMax=Integer.parseInt(st.nextToken());
			int platBMax=Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> [] platformB=new LinkedList [N];
			for (int n=0;n<N;n++) platformB[n]=new LinkedList<>();

			Stack<Integer> carrier=new Stack<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int B=Integer.parseInt(st.nextToken());
				for (int b=0;b<B;b++) platformB[n].addLast(Integer.parseInt(st.nextToken())-1);
			}
			
			int ans=0;
			int currN=0;
			while (true) {
				while (!carrier.isEmpty() && (platformB[currN].size()<platBMax || carrier.peek()==currN)) {
					int dest=carrier.pop();
					if (dest!=currN) platformB[currN].addLast(dest);
					ans++;
				}
				
				while (!platformB[currN].isEmpty() && carrier.size()<carrierMax) {
					carrier.push(platformB[currN].removeFirst());
					ans++;
				}
				
				currN=(currN+1)%N;
				
				boolean done=carrier.isEmpty();
				for (int n=0;n<N;n++) done &= platformB[n].isEmpty();
				if (done) break;
				
				ans+=2;
			}
			
			System.out.println(ans);
		}
	}

}
