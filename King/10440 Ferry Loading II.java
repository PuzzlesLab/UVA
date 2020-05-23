import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int [] car=new int [M];
			for (int i=0;i<M;i++) car[i]=Integer.parseInt(br.readLine());
			
			int currTime=0, trip=0, carLoaded=0, ferrySide=0, startIdx=0;
			if (M>N && M%N!=0) {
				startIdx=M%N;
				currTime=car[startIdx-1]+T;
				ferrySide=1;
				trip++;
			}
			
			for (int i=startIdx;i<M;i++) {
				if (ferrySide==1) {
					currTime+=T;
					carLoaded=0;
					ferrySide=0;
				}
				if (ferrySide==0) {
					currTime=Math.max(currTime, car[i]);
					carLoaded++;
					if (carLoaded==N || i==M-1) {
						trip++;
						currTime+=T;
						ferrySide=1;
					}
				}
			}
			
			System.out.printf("%d %d\n", currTime, trip);
		}
	}

}