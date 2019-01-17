import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static boolean isNeighbour (int first, int second) {
		return Integer.bitCount(first^second)==1;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			boolean [] hasPlayer=new boolean [(int)(Math.pow(2, N))];
			Arrays.fill(hasPlayer, 0, hasPlayer.length, true);
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) hasPlayer[Integer.parseInt(st.nextToken())-1]=false;
			
			int count=0;
			for (int n=N;n>0;n--) {
				boolean [] newFlag=new boolean [(int)Math.pow(2, N-1)];
				for (int i=0;i<hasPlayer.length;i+=2) {
					newFlag[i/2]=hasPlayer[i] || hasPlayer[i+1];
					if (hasPlayer[i] ^ hasPlayer[i+1]) count++;
				}
				hasPlayer=newFlag;
			}
			System.out.println(count);
		}
	}

}
