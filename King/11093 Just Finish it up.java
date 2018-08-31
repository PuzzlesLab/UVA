import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// The input lines are messed up, so we need to make it line independent!
class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int testCaseCount=Integer.parseInt(st.nextToken());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int [] p=new int [N];
			int [] q=new int [N];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) {
				if (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
				p[i]=Integer.parseInt(st.nextToken());
			}
			for (int i=0;i<N;i++) {
				if (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
				q[i]=Integer.parseInt(st.nextToken());
			}
			
			int currPetrol=0;
			boolean fullPass=false;
			int ans=-1;
			for (int start=0;!fullPass;) {
				if (start==N-1) fullPass=true;
				
				int i=start;
				currPetrol=0;
				do {
					currPetrol=currPetrol+p[i]-q[i];
					if (currPetrol<0) {
						start=(i+1)%N;
						currPetrol=-1;
						break;
					} 
					i=(i+1)%N;
					if (i==N-1) fullPass=true;
				} while (i!=start);
				if (currPetrol>=0) {
					ans=start;
					break;
				}
			}
			
			if (ans!=-1) System.out.println("Case "+testCase+": Possible from station "+(ans+1));
			else  System.out.println("Case "+testCase+": Not possible");
			
		}
	}

}
