import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			char [][] dnaSeq=new char[M][];
			for (int m=0;m<M;m++) dnaSeq[m]=br.readLine().toCharArray();
			
			char [] sol=new char[N];
			int hamming=0;
			for (int n=0;n<N;n++) {
				int [] occureence=new int[128];
				int maxOccur=0;
				for (int m=0;m<M;m++) {
					occureence[dnaSeq[m][n]]++;
					maxOccur=Math.max(maxOccur,occureence[dnaSeq[m][n]]);
				}
				for (int i=0;i<occureence.length;i++) if (occureence[i]==maxOccur) {
					sol[n]=(char)i;
					hamming+=(M-maxOccur);
					break;
				}
			}
			System.out.println(new String(sol));
			System.out.println(hamming);
		}
	}
}